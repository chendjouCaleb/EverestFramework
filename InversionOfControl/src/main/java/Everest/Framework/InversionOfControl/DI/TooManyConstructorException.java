package Everest.Framework.InversionOfControl.DI;

/**
 * Exceptions to throw when a implementation type of a component have many constructor
 * without one decorated by {@link Everest.Framework.Core.Inject.Resolve} annotation.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class TooManyConstructorException extends RuntimeException{
    public TooManyConstructorException() {
    }

    public TooManyConstructorException(String message) {
        super(message);
    }

    public TooManyConstructorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyConstructorException(Throwable cause) {
        super(cause);
    }

    public TooManyConstructorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
