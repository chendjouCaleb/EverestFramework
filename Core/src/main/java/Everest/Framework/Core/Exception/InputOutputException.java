package Everest.Framework.Core.Exception;

import java.io.IOException;

public class InputOutputException extends RuntimeException{
    private IOException ioException;

    public InputOutputException(IOException ioException) {
        super(ioException);
        this.ioException = ioException;
    }

    public InputOutputException(IOException ioException, String message) {
        super(message, ioException);
        this.ioException = ioException;
    }

    public InputOutputException(String message, boolean enableSuppression, boolean writableStackTrace, IOException ioException) {
        super(message, ioException, enableSuppression, writableStackTrace);
        this.ioException = ioException;
    }

    public IOException getIoException() {
        return ioException;
    }
}
