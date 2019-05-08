package Everest.Framework.Core.Inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicated that the decorated annotation define a scope on component.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
}
