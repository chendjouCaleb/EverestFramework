package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.Lookup.*;
import Everest.Framework.InversionOfControl.Utils.InvokerUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Used to create the usable instance of an {@link TypeComponent}.
 *
 * @author Chendjou
 * @version 1
 * @see TypeComponent
 * @since 01-05-2019
 */
public class TypeComponentResolver implements IComponentResolver<TypeComponent> {
    private FieldLookup fieldLookup;
    private ParametersLookup parametersLookup;
    public TypeComponentResolver(LookupEngine lookupEngine) {
        this.fieldLookup = new FieldLookup(lookupEngine, new CollectionLookup(lookupEngine));
        parametersLookup = new ParametersLookup(new ParameterLookup(lookupEngine));
    }

    @Override
    public Object resolve(@Nonnull TypeComponent component) {
        Constructor constructor = component.getInjectionConstructor();
        Object instance = resolveConstructor(constructor);

        for (Method method : component.getInjectionMethods()) {
            invokeMethod(instance, method);
        }

        for (Field field : component.getInjectionFields()) {
            resolveField(instance, field);
        }
        return instance;
    }

    private void invokeMethod(Object instance, Method method) {
        Parameter[] parameters = method.getParameters();

        Object[] values = parametersLookup.look(parameters);
        Reflexions.callRemote(instance, method, values);
    }

    private Object resolveConstructor(Constructor constructor) {
        Parameter[] parameters = constructor.getParameters();
        return InvokerUtils.invokeConstructor(constructor, parametersLookup.look(parameters));
    }

    /**
     * Resolve the dependencies of specified object on the specified field.
     * @param value The object to look his dependencies.
     * @param field The field to look on the object.
     */
    public void resolveField(Object value, Field field){
        if(value == null){
            throw new NullArgumentException("object");
        }
        if(field == null) {
            throw new NullArgumentException("field");
        }
        field.setAccessible(true);
        try {
            field.set(value, fieldLookup.look(field));
        } catch (IllegalAccessException e) {
            throw new ResolutionException(e);
        }
    }
}
