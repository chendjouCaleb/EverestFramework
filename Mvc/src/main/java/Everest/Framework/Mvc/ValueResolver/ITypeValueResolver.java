package Everest.Framework.Mvc.ValueResolver;

import Everest.Framework.Mvc.Action.ActionContext;

import java.lang.reflect.Parameter;

/**
 * Base interface for class which an look action method parameter based on the parameter class.
 *
 * @param <T> The type of the parameter to look value.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface ITypeValueResolver<T, I extends ActionContext> {

    /**
     * Resolve the parameter value.
     * @param actionContext The action context of the request.
     * @param parameter The parameter to look her value.
     * @return The resolved value.
     */
    T getValue(I actionContext, Parameter parameter);
}
