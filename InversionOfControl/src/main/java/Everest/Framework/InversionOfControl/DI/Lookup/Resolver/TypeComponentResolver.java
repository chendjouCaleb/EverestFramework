package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ResolutionException;
import Everest.Framework.InversionOfControl.Utils.InvokerUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Used to create the usable instance of an {@link TypeComponent}.
 *
 * @author Chendjou
 * @version 1
 * @see TypeComponent
 * @since 01-05-2019
 */
public class TypeComponentResolver implements IComponentResolver<TypeComponent> {
    private NamedLookup namedLookup;
    private LookupEngine lookupEngine;

    public TypeComponentResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.namedLookup = namedLookup;
        this.lookupEngine = lookupEngine;
    }

    @Override
    public Object resolve(TypeComponent component) {
        Constructor constructor = component.getInjectionConstructor();
        Object instance = resolveConstructor(constructor);

        for(Method method: component.getInjectionMethods()){
            invokeMethod(instance, method);
        }

        for (Field field: component.getInjectionFields()){
            resolveField(instance, field);
        }

        return instance;
    }

    private void resolveField(Object object, Field field){
        field.setAccessible(true);
        try {
            field.set(object, lookupField(field));
        } catch (IllegalAccessException e) {
            throw new ResolutionException(e);
        }
    }

    private Object lookupField(Field field){

        UseNamed named = field.getAnnotation(UseNamed.class);
        if (named != null) {
            return namedLookup.look(named.value());
        } else {
            return lookupEngine.look(field.getType());
        }
    }

    private void invokeMethod(Object instance, Method method){
        Parameter[] parameters = method.getParameters();
        List<Object> values = new ArrayList<>(parameters.length);

        for (Parameter parameter : parameters) {
            UseNamed named = parameter.getAnnotation(UseNamed.class);
            if (named != null) {
                values.add(namedLookup.look(named.value()));
            } else {
                values.add(lookupEngine.look(parameter.getType()));
            }
        }
        Reflexions.callRemote(instance, method, values.toArray());
    }

    private Object resolveConstructor(Constructor constructor){
        Parameter[] parameters = constructor.getParameters();
        List<Object> values = new ArrayList<>(parameters.length);

        for (Parameter parameter : parameters) {
            UseNamed named = parameter.getAnnotation(UseNamed.class);
            if (named != null) {
                values.add(namedLookup.look(named.value()));
            } else {
                values.add(lookupEngine.look(parameter.getType()));
            }
        }
        return InvokerUtils.invokeConstructor(constructor, values.toArray());
    }

}
