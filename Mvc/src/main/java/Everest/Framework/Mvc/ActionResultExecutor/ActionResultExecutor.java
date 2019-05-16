package Everest.Framework.Mvc.ActionResultExecutor;

import Everest.Framework.Core.Exception.InputOutputException;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Http.StatusCode;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Framework.Mvc.Result.EntityResult;
import Everest.Framework.Mvc.Result.IActionResult;

import java.io.IOException;

/**
 * The component that execute the result returned by an action methods.
 * @see IResultExecutor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Singleton
public class ActionResultExecutor {

    /**
     * The {@link IResultExecutor} instance provider.
     */
    private ActionResultExecutorProvider executorProvider;

    public ActionResultExecutor(ActionResultExecutorProvider executorProvider) {
        this.executorProvider = executorProvider;
    }
    public void execute(HttpContext httpContext, IActionResult result){
        ActionInvocationResult invocationResult = new ActionInvocationResult();
        invocationResult.setObjectResult(result);
        execute(httpContext, invocationResult);
    }

    /**
     * Execute the result of an action method
     * @param context The {@link HttpContext } of the http request
     * @param result The result returned by the action method invocation
     */
    public void execute(HttpContext context, ActionInvocationResult result){
        HttpResponse response = context.getResponse();
        if(result.isVoid()){
            response.setStatusCode(StatusCode.NO_CONTENT.value());
            response.write("");
            return;
        }
        //If the result is null, we assign the string 'null' to result prior to continue the execution
        if(result.getObjectResult() == null){
            result.setObjectResult("null");
        }

        Class type = result.getResultType();

        IResultExecutor resultExecutor = executorProvider.getExecutor(type);

        /*
         * If there are no resultExecutor for the type of the result
         * The EntityResultExecutor is selected and the result become an EntityResult with the old result as setBody.
         */
        if(resultExecutor == null){
            resultExecutor = executorProvider.getExecutor(EntityResult.class);
            EntityResult<?> entityResult = new EntityResult(result.getObjectResult());
            result.setObjectResult(entityResult);
        }

        try {
            resultExecutor.execute(context, result.getObjectResult());
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }
}
