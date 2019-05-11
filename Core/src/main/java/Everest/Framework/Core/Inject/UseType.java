package Everest.Framework.Core.Inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uses it to set the component type of a class for injection.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface UseType {
    Class<?> value();
}
