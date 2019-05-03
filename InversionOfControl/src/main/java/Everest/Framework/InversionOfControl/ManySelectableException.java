package Everest.Framework.InversionOfControl;

public class ManySelectableException extends RuntimeException {
    public ManySelectableException() {
    }

    public ManySelectableException(String message) {
        super(message);
    }

    public ManySelectableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManySelectableException(Throwable cause) {
        super(cause);
    }

    public ManySelectableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
