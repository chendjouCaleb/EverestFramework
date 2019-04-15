package Everest.Framework.Mvc.ExceptionHandler;


import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Middleware.IMiddleware;
import Everest.Framework.Mvc.Middleware.MiddlewareChain;

/**
 * The {@link Everest.Framework.Mvc.Middleware.IMiddleware} that catch and handled a raised exceptions.
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ExceptionHandlerMiddleware implements IMiddleware {
    private ExceptionManager exceptionManager;

    public ExceptionHandlerMiddleware(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }


    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        try{
            chain.executeNext(httpContext);
        }catch (Exception e){
            exceptionManager.handleError(e, httpContext);
        }
    }
}
