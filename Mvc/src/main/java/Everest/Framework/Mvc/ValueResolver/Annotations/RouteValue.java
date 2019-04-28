package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.Routing.RouteValues;
import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to inject route value in action method parameter.
 * @see RouteValues
 * @see Everest.Framework.Mvc.ValueResolver.TypedResolver.RouteDataResolver
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RouteValue {

    /**
     * The name of the target route parameter.
     * If is empty, the method parameter will be used as route parameter name.
     */
    String value() default "";
}
