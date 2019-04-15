package Everest.Framework.Mvc.ValueResolver;

/**
 * To throw when the method parameter value resolution fail.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ValueResolverException extends RuntimeException{
    public ValueResolverException() {
    }

    public ValueResolverException(String message) {
        super(message);
    }

    public ValueResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueResolverException(Throwable cause) {
        super(cause);
    }

    public ValueResolverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
