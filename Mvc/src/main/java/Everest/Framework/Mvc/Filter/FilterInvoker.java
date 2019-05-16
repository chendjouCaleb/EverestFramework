package Everest.Framework.Mvc.Filter;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationContext;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Framework.Mvc.ActionInvocation.ActionMethodInvoker;
import Everest.Framework.Mvc.ValueResolver.MethodValueResolver;

import java.lang.reflect.Method;

public class FilterInvoker {
    private ActionMethodInvoker methodInvoker;
    private MethodValueResolver methodValueResolver;

    public FilterInvocationResult invoke(Method method, Object filter, ActionContext actionContext){
        ActionInvocationContext invocationContext = new ActionInvocationContext();

        invocationContext.setController(filter);
        invocationContext.setMethod(method);
        Object[] params = methodValueResolver.getVariables(method, actionContext);

        ActionInvocationResult result = methodInvoker.invoke(invocationContext);
        return null;
    }
}
