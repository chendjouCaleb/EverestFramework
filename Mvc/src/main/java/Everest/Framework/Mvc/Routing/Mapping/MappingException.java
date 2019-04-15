package Everest.Framework.Mvc.Routing.Mapping;

/**
 * Exception to thrown when error occur during the mapping process.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingException extends RuntimeException {
    public MappingException() {
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }

    public MappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
