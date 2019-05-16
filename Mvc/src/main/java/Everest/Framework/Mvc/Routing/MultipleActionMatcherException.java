package Everest.Framework.Mvc.Routing;

/**
 * Exception to thrown when an url of incoming request match multiple action.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */
public class MultipleActionMatcherException extends RuntimeException{
    public MultipleActionMatcherException() {
    }

    public MultipleActionMatcherException(String message) {
        super(message);
    }

    public MultipleActionMatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleActionMatcherException(Throwable cause) {
        super(cause);
    }

    public MultipleActionMatcherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
