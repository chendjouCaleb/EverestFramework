package Everest.Framework.Mvc.ValueResolver.TypedResolver;


import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provides the {@link ActionContext} of the method execution.
 * @see ActionContext
 *
 * @author Chendjou
 * @version 1
 * @since 16-05-2019
 */
public class ActionContextRevolver implements ITypeValueResolver<ActionContext, ActionContext> {

    public ActionContext getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext;
    }
}
