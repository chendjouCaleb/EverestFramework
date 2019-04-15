package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for parameter which value must be an request HttpContext items.
 * @see Everest.Framework.Http.HttpContext
 * @see Everest.Framework.Mvc.ValueResolver.AnnotationResolver.ItemValueResolver
 * @see Everest.Framework.Http.ItemCollection
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ItemValue {

    /**
     * The name of the target item.
     * If is empty, the method parameter will be used as item name.
     */
    String value() default "";
}
