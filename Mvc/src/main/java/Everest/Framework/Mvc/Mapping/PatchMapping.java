package Everest.Framework.Mvc.Mapping;


import Everest.Framework.Http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to indicate that a method can be a target of a HTTP PATH Request.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@HttpMapping(verbs = HttpMethod.PATCH)
public @interface PatchMapping {
    String value() default "";
    String name() default "";
}
