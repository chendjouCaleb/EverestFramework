package Everest.Framework.InversionOfControl.DI.Lookup;

public class ResolutionException extends RuntimeException {
    public ResolutionException() {
    }

    public ResolutionException(String message) {
        super(message);
    }

    public ResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResolutionException(Throwable cause) {
        super(cause);
    }

    public ResolutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
