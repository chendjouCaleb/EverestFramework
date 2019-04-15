package Everest.Framework.Mvc.ExceptionHandler;


import java.util.Collection;

/**
 * The base interface for all exception handler.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface IExceptionHandler {
    Collection<Class<? extends Throwable>> getExceptionTypes();
    void handleException(ExceptionContext context);
}
