package Everest.Framework.Mvc.ValueResolver;

import Everest.Framework.Mvc.Action.ActionContext;

import java.lang.reflect.Parameter;

/**
 * Base interface for class which an resolve action method parameter based on the parameter class.
 *
 * @param <T> The type of the parameter to resolve value.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface ITypedValueResolver<T> {

    /**
     * Resolve the parameter value.
     * @param actionContext The action context of the request.
     * @param parameter The parameter to resolve her value.
     * @return The resolved value.
     */
    T getValue(ActionContext actionContext, Parameter parameter);
}
