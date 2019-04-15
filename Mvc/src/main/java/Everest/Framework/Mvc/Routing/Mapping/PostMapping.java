package Everest.Framework.Mvc.Routing.Mapping;



import Everest.Framework.Http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to indicate that a method can be a target of a HTTP POST Request.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@HttpMapping(verbs = HttpMethod.POST)
public @interface PostMapping {
    String value() default "";
    String name() default "";
}
