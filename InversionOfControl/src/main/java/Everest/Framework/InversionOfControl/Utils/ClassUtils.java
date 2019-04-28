package Everest.Framework.InversionOfControl.Utils;

/**
 * The utility class which contains methods to manipulate {@link Class} type.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ClassUtils {

    /**
     * Checks if a type is concrete, in other words, if we can invoke the constructor of object of this type.
     * @param type The type to check.
     * @return {@code true} if the type is concrete.
     */
    public static boolean isConcrete(Class<?> type){
        int mod = type.getModifiers();
        return (mod & 1536) == 0;
    }
}
