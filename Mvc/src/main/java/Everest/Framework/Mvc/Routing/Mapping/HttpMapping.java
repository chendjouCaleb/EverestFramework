package Everest.Framework.Mvc.Routing.Mapping;


import Everest.Framework.Http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to indicate that a method an Class can be a target of a HTTP Request.
 * It can also used to indicate that an annotation can be used can decorator of HTTP Request method.
 *
 * @see GetMapping
 * @see PostMapping
 * @see PutMapping
 * @see PathMapping
 * @see DeleteMapping
 * @see OptionsMapping
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface HttpMapping {


    /**
     * The url mapping of the action or controller.
     */
    String value() default "";

    /**
     * The verbs of a HTTP request which can invoke the annotated action.
     */
    HttpMethod verbs() default HttpMethod.GET;

    /**
     * The assigned name of the controller or action name.
     * Let it empty if you want to use the class name as
     * controller name and action name as action name.
     */
    String name() default "";
}
