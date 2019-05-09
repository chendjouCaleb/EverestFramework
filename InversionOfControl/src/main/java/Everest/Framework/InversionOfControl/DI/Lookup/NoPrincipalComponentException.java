package Everest.Framework.InversionOfControl.DI.Lookup;

/**
 * Exception to thrown when a groups of components with same type dont have a principal component.
 * @see Everest.Framework.InversionOfControl.DI.Abstractions.Component
 *
 * @author Chendjou
 * @version 1
 * @since 09-05-2019
 */
public class NoPrincipalComponentException extends RuntimeException {
    public NoPrincipalComponentException() {
    }

    public NoPrincipalComponentException(String message) {
        super(message);
    }

    public NoPrincipalComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPrincipalComponentException(Throwable cause) {
        super(cause);
    }

    public NoPrincipalComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
