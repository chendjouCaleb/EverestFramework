package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.Utils.InvokerUtils;

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
    private NamedLookup namedLookup;
    private LookupEngine lookupEngine;
    private ParametersResolver parametersResolver;

    public TypeComponentResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.namedLookup = namedLookup;
        this.lookupEngine = lookupEngine;
        parametersResolver = new ParametersResolver(new ParameterResolver(lookupEngine, namedLookup));
    }

    @Override
    public Object resolve(TypeComponent component) {
        Constructor constructor = component.getInjectionConstructor();
        Object instance = resolveConstructor(constructor);

        for (Method method : component.getInjectionMethods()) {
            invokeMethod(instance, method);
        }

        for (Field field : component.getInjectionFields()) {
            FieldResolver fieldResolver = new FieldResolver(lookupEngine, namedLookup);
            fieldResolver.resolveField(instance, field);
        }
        return instance;
    }

    private void invokeMethod(Object instance, Method method) {
        Parameter[] parameters = method.getParameters();

        Object[] values = parametersResolver.resolve(parameters);
        Reflexions.callRemote(instance, method, values);
    }

    private Object resolveConstructor(Constructor constructor) {
        Parameter[] parameters = constructor.getParameters();
        return InvokerUtils.invokeConstructor(constructor, parametersResolver.resolve(parameters));
    }

}
