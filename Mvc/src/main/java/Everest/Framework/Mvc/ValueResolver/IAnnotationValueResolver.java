package Everest.Framework.Mvc.ValueResolver;

import Everest.Framework.Mvc.Action.ActionContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * Base interface for class which an resolve action method parameter based on the parameter annotation.
 * @param <T> The annotation type of the target parameter.
 * T must be annotated by {@link ValueResolver}.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface IAnnotationValueResolver<T extends Annotation> {

    /**
     * Resolves the parameter value.
     * @param actionContext The action context of the request.
     * @param parameter The method parameter to resolve.
     * @param annotation The annotation of the method parameter.
     * @return The resolved value.
     */
    Object getVariable(ActionContext actionContext, Parameter parameter, T annotation);
}
