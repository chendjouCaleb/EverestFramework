package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * * Annotation for parameter which value must be an request Form value.
 * @see Everest.Framework.Mvc.ValueResolver.AnnotationResolver.FormValueResolver
 * @see Everest.Framework.Http.FormCollection
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface FormValue {

    /**
     * The name of the target form field.
     * If is empty, the whole form data must be used.
     */
    String value() default "";
}
