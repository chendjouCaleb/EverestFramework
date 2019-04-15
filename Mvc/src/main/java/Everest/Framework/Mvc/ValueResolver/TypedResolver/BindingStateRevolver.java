package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Binding.BindingState;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;


import java.lang.reflect.Parameter;

/**
 * Provider the data BindingState of the request.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class BindingStateRevolver implements ITypedValueResolver<BindingState> {

    public BindingState getValue(ActionContext actionContext, Parameter parameter) {

        return actionContext.getBindingState();
    }


}
