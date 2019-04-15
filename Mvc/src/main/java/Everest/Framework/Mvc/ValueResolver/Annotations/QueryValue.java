package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for parameter which value must be an request Query value.
 * @see Everest.Framework.Http.QueryCollection

 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryValue {

    /**
     * The name of the target query parameter. If is empty, the method parameter will be used as name.
     */
    String value() default "";
}
