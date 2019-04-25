package Everest.Framework.Core.Exception;

public class MalformedStringException extends RuntimeException {
    public MalformedStringException() {
    }

    public MalformedStringException(String message) {
        super(message);
    }

    public MalformedStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedStringException(Throwable cause) {
        super(cause);
    }

    public MalformedStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
