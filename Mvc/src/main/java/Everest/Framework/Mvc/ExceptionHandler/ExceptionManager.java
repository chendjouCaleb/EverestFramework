package Everest.Framework.Mvc.ExceptionHandler;

import Everest.Framework.Core.Decorator.Instance;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Framework.Mvc.ActionResultExecutor.ActionResultExecutor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

/**
 * Manage the exception raised during the Mvc pipeline execution.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Instance
public class ExceptionManager {
    private Logger logger = LoggerFactory.getLogger(ExceptionManager.class);
    private ActionResultExecutor resultExecutor;
    private ExceptionHandlerProvider handlerProvider;
    private DefaultExceptionHandler defaultExceptionHandler;

    public ExceptionManager(ActionResultExecutor resultExecutor, ExceptionHandlerProvider handlerProvider, DefaultExceptionHandler defaultExceptionHandler) {
        this.resultExecutor = resultExecutor;
        this.handlerProvider = handlerProvider;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    public void handleError(Throwable exception, HttpContext httpContext){
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }

        logger.info("The {} was thrown: ", rootException.getClass());
        logger.error("Erreur durant l'exécution de la requete.", rootException);

        ExceptionContext exceptionContext = new ExceptionContext(rootException, httpContext);

        try {
            IExceptionHandler exceptionHandler = handlerProvider.getExceptionHandler(rootException.getClass());
            logger.info("Exception handlers: {}.", exceptionHandler.getClass());
            exceptionHandler.handleException(exceptionContext);
        }catch (NoSuchElementException e){
            logger.info("The default exception handlers is used.");
            defaultExceptionHandler.handleException(exceptionContext);
        }


        if(exceptionContext.getResult() != null){
            ActionInvocationResult result = new ActionInvocationResult();
            result.setObjectResult(exceptionContext.getResult());
            resultExecutor.execute(httpContext, result);
        }


    }
}
