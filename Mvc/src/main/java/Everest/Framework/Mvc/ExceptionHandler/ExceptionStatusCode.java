package Everest.Framework.Mvc.ExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inject to indicate the HTTP status code for non handled exception.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExceptionStatusCode {
    int value() default 400;
}
