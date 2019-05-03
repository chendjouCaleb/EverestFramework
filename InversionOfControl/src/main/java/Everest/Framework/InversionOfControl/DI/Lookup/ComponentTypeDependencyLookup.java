package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Use to find dependent components of  {@link TypeComponent}.
 * @see TypeComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class ComponentTypeDependencyLookup {

    /**
     * Looks dependency of implementation type constructor
     * @param component The component to find dependencies.
     * @param components All available components.
     * @return The constructor dependencies.
     */
    public List<Component> lookConstructorDependencies(TypeComponent component, ComponentCollection components){
        return new ArrayList<>();
    }

    /**
     * Looks dependency of implementation type fields.
     * @param component The component to find dependencies.
     * @param components All available components.
     * @return The field dependencies.
     */
    public List<Component> lookFieldDependencies(TypeComponent component, ComponentCollection components){
        return new ArrayList<>();
    }

    /**
     * Looks dependency of implementation type methods.
     * @param component The component to find dependencies.
     * @param components All available components.
     * @return The method dependencies.
     */
    public List<Component> lookMethodDependencies(TypeComponent component, ComponentCollection components){
        return new ArrayList<>();
    }
}
