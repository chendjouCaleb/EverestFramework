package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Use it to get all injection methods of the implementation type of a component.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class InjectionMethodGetter {

    /**
     * Get all injection methods of a component implementation type.
     *
     * An injection method is decorated by {@link Resolve}.
     *
     * @param type The implementation type of an component.
     * @return An {@code ArrayList} containing all injection methods of the type.
     */
    public List<Method> getInjectionMethods(Class type) {
        List<Method> methods = new ArrayList<>();
        Class current = type;
        while (current != Object.class){
            for(Method method: current.getDeclaredMethods()){
                System.out.println(method);
                if(method.isAnnotationPresent(Resolve.class)){
                    methods.add(method);
                }
            }
            current = current.getSuperclass();
        }

        return methods;
    }
}
