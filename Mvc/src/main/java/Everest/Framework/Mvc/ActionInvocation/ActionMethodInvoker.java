package Everest.Framework.Mvc.ActionInvocation;


import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Core.Reflexions;

/**
 * The component that execution action method
 */
@Singleton
public class ActionMethodInvoker {

    public ActionInvocationResult invoke(ActionInvocationContext context){
        ActionInvocationResult result = new ActionInvocationResult();

        try {
            Object obj = Reflexions.callRemote(context.getController(), context.getMethod(), context.getParams());
            if(context.getMethod().getReturnType().equals(void.class)) {
                result.setVoid(true);
                return result;
            }


            if(obj == null){
                result.setNull(true);
                return result;
            }
            result.setObjectResult(obj);
        } catch (Exception e) {
            throw new ActionInvocationException(e);
        }
        return result;
    }
}
