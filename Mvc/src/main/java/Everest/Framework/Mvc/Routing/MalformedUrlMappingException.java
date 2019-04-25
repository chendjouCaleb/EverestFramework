package Everest.Framework.Mvc.Routing;

/**
 * Exception to thrown when a controller or acton method an a malformed url mapping.
 *
 * @author Chendjou
 * @version 1
 * @since 25-04-2019
 */
public class MalformedUrlMappingException extends RuntimeException{
    public MalformedUrlMappingException() {
    }

    public MalformedUrlMappingException(String message) {
        super(message);
    }

    public MalformedUrlMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedUrlMappingException(Throwable cause) {
        super(cause);
    }

    public MalformedUrlMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
