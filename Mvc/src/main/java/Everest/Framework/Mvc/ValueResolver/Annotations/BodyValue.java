package Everest.Framework.Mvc.ValueResolver.Annotations;

import Everest.Framework.Mvc.ValueResolver.ValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValueResolver
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BodyValue {
    String value() default "";
}
