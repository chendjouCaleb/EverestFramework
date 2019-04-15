package Everest.Framework.Mvc.ValueResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

/**
 * Decorated the Annotations which indicates that the method parameter can be resolved.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Target(ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueResolver {
    Class<?> value() default Object.class;
}
