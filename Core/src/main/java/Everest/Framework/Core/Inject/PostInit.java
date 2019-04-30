package Everest.Framework.Core.Inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method will be executed after the initialisation of the component.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface PostInit {
    String name() default "";
}

