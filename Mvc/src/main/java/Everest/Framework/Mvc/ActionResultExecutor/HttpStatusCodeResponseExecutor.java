package Everest.Framework.Mvc.ActionResultExecutor;


import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.StatusCode;

/**
 * Execute a {@link StatusCode} result.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class HttpStatusCodeResponseExecutor implements IResultExecutor<StatusCode> {

    public void execute(HttpContext httpContext, StatusCode code) {

        httpContext.getResponse().setStatusCode(code.value());
        httpContext.getResponse().write("");
    }
}
