package Everest.Framework.Core.Exception;

/**
 * Exception to thrown when a component have an invalid name.
 *
 * @author Chendjou
 * @version 1
 * @since 25-04-2019
 */
public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
    }

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException(Throwable cause) {
        super(cause);
    }

    public InvalidNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
