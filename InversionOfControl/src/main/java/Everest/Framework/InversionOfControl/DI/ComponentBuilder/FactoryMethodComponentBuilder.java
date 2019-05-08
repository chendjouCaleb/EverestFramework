package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Annotations;
import Everest.Framework.Core.Inject.Principal;
import Everest.Framework.Core.Inject.Scope;
import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the {@link FactoryMethodComponent}.
 * This builder don't implements {@link ComponentBuilder} because the mechanisms to build a {@link FactoryMethodComponent}
 * is different of all other {@link Component} builder mechanisms.
 *
 * @see FactoryMethodComponent
 * @see Component
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class FactoryMethodComponentBuilder {
    private AnnotationLifeTimeGetter annotationLifeTimeGetter = new AnnotationLifeTimeGetter();
    private FactoryMethodScanner factoryMethodScanner = new FactoryMethodScanner();

    /**
     * Builds {@link FactoryMethodComponent} based on implementation type of the specified {@link TypeComponent}.
     * @param typeComponent The component in which we get all {@link FactoryMethodComponent}.
     * @return a {@link ArrayList} containing all
     * {@link FactoryMethodComponent} found in the implementation type of the specified type component.
     */
    public List<FactoryMethodComponent> buildComponents(@Nonnull TypeComponent typeComponent){
        List<FactoryMethodComponent> components = new ArrayList<>();
        List<Method> factoryMethods = factoryMethodScanner.getFactoryMethods(typeComponent.getImplementationType());

        for(Method method: factoryMethods){
            FactoryMethodComponent component = buildComponent(method, typeComponent);
            components.add(component);
        }

        return components;
    }

    /**
     * Creates a {@link FactoryMethodComponent}.
     * @param method The method of the created component.
     * @param typeComponent The parent component of the created component.
     * @return The created component.
     * @throws IllegalStateException If the method is decorated by {@link UseNamed} annotation with empty value.
     */
    public FactoryMethodComponent buildComponent(@Nonnull Method method, @Nonnull TypeComponent typeComponent) {
        FactoryMethodComponent component = new FactoryMethodComponent(method, typeComponent);

        Annotation scopeAnnotation = Annotations.annotatedAnnotation(method, Scope.class);
        ComponentLifetime lifetime = annotationLifeTimeGetter.getLifeTime(scopeAnnotation.annotationType());
        component.setLifetime(lifetime);

        component.setComponentType(method.getReturnType());
        if(method.isAnnotationPresent(Principal.class)){
            component.setPrincipal(true);
        }

        UseNamed useNamed = method.getAnnotation(UseNamed.class);
        if(useNamed != null){
            if("".equals(useNamed.value())){
                throw new IllegalStateException("You cannot use a empty string as name of component. Method: " + method);
            }
            component.setName(useNamed.value());
        }

        return component;
    }
}
