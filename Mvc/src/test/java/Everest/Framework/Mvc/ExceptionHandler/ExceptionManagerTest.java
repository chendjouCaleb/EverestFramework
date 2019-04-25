package Everest.Framework.Mvc.ExceptionHandler;
import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Http.DefaultHttpContext;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ActionResultExecutor.ActionResultExecutor;
import Everest.Framework.Mvc.ActionResultExecutor.ActionResultExecutorProvider;
import Everest.Framework.Mvc.ActionResultExecutor.IResultExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionManagerTest {

    private ActionResultExecutorProvider executorProvider;
    private ExceptionHandlerProvider handlerProvider;
    private ActionResultExecutor resultExecutor;
    private ExceptionManager exceptionManager;

    @BeforeEach
    void setUp() {
        handlerProvider = new ExceptionHandlerProvider();
        executorProvider = new ActionResultExecutorProvider();
        resultExecutor = new ActionResultExecutor(executorProvider);
        exceptionManager = new ExceptionManager(resultExecutor, handlerProvider, new DefaultExceptionHandler(new ExceptionStatusCodeGetter()));

        handlerProvider.addExceptionHandler(new InvalidOperationExceptionHandler());
        handlerProvider.addExceptionHandler(new NoSuchElementExceptionHandler());

        executorProvider.addExecutor(new SimpleResultExecutor());
    }

    @Test
    void handleError_With_Null_Result() {
        InvalidOperationException invalidOperationException = new InvalidOperationException("message of exception");
        HttpContext httpContext = new DefaultHttpContext();

        exceptionManager.handleError(invalidOperationException, httpContext);

        assertEquals(httpContext.getResponse().writer().toString(), invalidOperationException.getMessage());
    }

    @Test
    void handleException_With_ResultContext(){
        NoSuchElementException exception = new NoSuchElementException("Element not found");
        HttpContext httpContext = new DefaultHttpContext();

        exceptionManager.handleError(exception, httpContext);
        assertEquals(exception.getMessage(), httpContext.getResponse().writer().toString());
        assertEquals(404, httpContext.getResponse().statusCode());

    }

    private class InvalidOperationExceptionHandler implements IExceptionHandler{
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
           return Collections.singletonList(InvalidOperationException.class);
        }

        public void handleException(ExceptionContext context) {
            context.getHttpContext().getResponse().write(context.getException().getMessage());
        }
    }

    private class NoSuchElementExceptionHandler implements IExceptionHandler{
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
            return Collections.singletonList(NoSuchElementException.class);
        }

        public void handleException(ExceptionContext context) {
            SimpleResult result = new SimpleResult(context.getException().getMessage(), 404);
            context.setResult(result);
        }
    }

    private class SimpleResultExecutor implements IResultExecutor<SimpleResult> {

        @Override
        public void execute(HttpContext httpContext, SimpleResult result) {
            httpContext.getResponse().write(result.getMessage());
            httpContext.getResponse().setStatusCode(result.getStatusCode());
        }
    }

    private class SimpleResult{
        private String message;
        protected int statusCode;

        public SimpleResult(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }
    }
}