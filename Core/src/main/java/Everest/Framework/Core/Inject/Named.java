package Everest.Framework.Core.Inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uses it to set the name to component that will be injected.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Named {
    String value();
}
