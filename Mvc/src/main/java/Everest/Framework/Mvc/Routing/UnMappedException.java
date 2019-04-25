package Everest.Framework.Mvc.Routing;


/**
 * Exception to thrown when a expected mapped component is not mapped
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class UnMappedException extends RuntimeException{
    public UnMappedException() {
    }

    public UnMappedException(String message) {
        super(message);
    }

    public UnMappedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnMappedException(Throwable cause) {
        super(cause);
    }

    public UnMappedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
