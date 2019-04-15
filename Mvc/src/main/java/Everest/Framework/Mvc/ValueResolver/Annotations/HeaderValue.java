package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for parameter which value must be an request Header value.
 * @see Everest.Framework.Mvc.ValueResolver.AnnotationResolver.HeaderValueResolver
 * @see Everest.Framework.Http.HeaderCollection
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HeaderValue {

    /**
     * The name of the target header.
     * If is empty, the method parameter will be used as header name.
     * @return The name of the header parameter.
     */
    String value() default "";
}
