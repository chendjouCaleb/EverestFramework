package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;

/**
 * Use it to get all injection constructor of a component implementation type.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class InjectionConstructorGetter {

    /**
     * Get all injection constructors of a component implementation type.
     *
     * An injection field is decorated by {@link Resolve}.
     *
     * @param type The implementation type of an component.
     * @return An {@code ArrayList} containing all injection constructors of the type.
     */
    public Constructor getConstructor(@Nonnull Class type){
        Constructor[] constructors = type.getConstructors();

        if(constructors.length == 1){
            return constructors[0];
        }

        Constructor selectedConstructor = null;

        for(Constructor constructor: constructors){
            if(constructor.isAnnotationPresent(Resolve.class)){
                if(selectedConstructor != null){
                    throw new IllegalStateException(String.format("The component '%s' have to many constructor annotated" +
                            " with @Resolve annotation", type.getName()));
                }
                selectedConstructor = constructor;
            }
        }
        if(selectedConstructor != null){
            return selectedConstructor;
        }

        throw new NoSuchElementException( String.format("The component '%s' have many constructor or is not concrete type. use '%s' " +
                "annotation to select to default constructor", type.getName(), Resolve.class.getName()));
    }
}
