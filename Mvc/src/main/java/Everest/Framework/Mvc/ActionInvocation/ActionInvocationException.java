package Everest.Framework.Mvc.ActionInvocation;

/**
 * To thrown when the invocation of action method fail.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ActionInvocationException extends RuntimeException {
    public ActionInvocationException() {
    }

    public ActionInvocationException(String message) {
        super(message);
    }

    public ActionInvocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionInvocationException(Throwable cause) {
        super(cause);
    }

    public ActionInvocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
