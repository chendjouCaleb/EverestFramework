package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.PostInit;
import Everest.Framework.Core.Inject.Resolve;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Use it to get all post initialisation methods of the implementation type of a component.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class PostInitMethodGetter {

    /**
     * Get all post initialisation methods of a component implementation type.
     *
     * An post initialisation method is decorated by {@link Everest.Framework.Core.Inject.PostInit}.
     *
     * @param type The implementation type of an component.
     * @return An {@code ArrayList} containing all post initialisation methods of the type.
     */
    public List<Method> getPostInitMethods(Class type) {
        List<Method> methods = new ArrayList<>();
        Class current = type;
        while (current != Object.class){
            for(Method method: current.getDeclaredMethods()){
                if(method.isAnnotationPresent(PostInit.class)){
                    if(method.getParameters().length > 0){
                        throw new IllegalStateException("A post initialisation method dont have a parameters");
                    }
                    methods.add(method);
                }
            }
            current = current.getSuperclass();
        }

        return methods;
    }
}
