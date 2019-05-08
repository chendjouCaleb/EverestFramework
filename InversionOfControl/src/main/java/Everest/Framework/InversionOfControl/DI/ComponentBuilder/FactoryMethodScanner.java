package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Annotations;
import Everest.Framework.Core.Inject.Scope;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterates on methods of a class an detect a method which can be a method
 * of an {@link Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent}.
 * @see Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class FactoryMethodScanner {

    /**
     * Gets all factory methods of a specified type.
     * @param type The type in which we find a methods.
     * @return A {@link ArrayList} containing all factory methods of the type.
     */
    public List<Method> getFactoryMethods(@Nonnull Class type) {
        List<Method> factoryMethods = new ArrayList<>();

        for (Method method: type.getMethods()){
            if(isFactoryMethod(method)){
                factoryMethods.add(method);
            }
        }

        return factoryMethods;
    }

    /**
     * Checks that if a method is a factory method.
     * A method is a factory method if this method is decorated by
     * an annotation decorated by {@link Scope} annotation.
     *
     * @param method The method to check.
     * @return {@code true} is the specified method is factory method.
     */
    public boolean isFactoryMethod(@Nonnull Method method) {
        Annotation annotation = Annotations.annotatedAnnotation(method, Scope.class);
        return annotation != null;
    }
}
