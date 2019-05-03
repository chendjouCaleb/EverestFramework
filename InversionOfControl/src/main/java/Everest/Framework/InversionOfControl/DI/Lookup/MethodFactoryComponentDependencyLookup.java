package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Use to find dependent components of  {@link Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent}.
 * @see TypeComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class MethodFactoryComponentDependencyLookup {

    /**
     * Finds all dependencies of a method based on its parameters
     * @param method The component method provider.
     * @param components Available components in which to find dependencies.
     * @return Dependencies of the specified method component.
     */
    List<Component> lookDependencies(Method method, ComponentCollection components) {
        return new ArrayList<>();
    }
}
