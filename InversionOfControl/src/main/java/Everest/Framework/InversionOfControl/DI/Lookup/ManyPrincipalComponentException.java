package Everest.Framework.InversionOfControl.DI.Lookup;

/**
 * Exception to thrown when a groups of components with same type multiple principal components.
 * @see Everest.Framework.InversionOfControl.DI.Abstractions.Component
 *
 * @author Chendjou
 * @version 1
 *
 * @since 09-05-2019
 */
public class ManyPrincipalComponentException extends RuntimeException {
    public ManyPrincipalComponentException() {
    }

    public ManyPrincipalComponentException(String message) {
        super(message);
    }

    public ManyPrincipalComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManyPrincipalComponentException(Throwable cause) {
        super(cause);
    }

    public ManyPrincipalComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
