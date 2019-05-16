package Everest.Framework.Mvc.Filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Indicates that the annotated method must be executed after the action method.
 *
 * @author Chendjou
 * @version 1
 * @since 12-05-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface OnActionExecuted {
    int order() default 0;
}
