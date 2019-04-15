package Everest.Framework.Core.Decorator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that the value of the decorated element can be resolved by the DI container
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER})
public @interface Resolve {
    String qualifier() default "";
}
