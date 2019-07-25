package Everest.Framework.Http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a file sent with the HttpRequest.
 */
public interface IFormFile {

    /**
     * Gets the raw Content-Type header of the uploaded file.
     */
    String getContentType();

    /**
     * Gets the file name from the Content-Disposition header.
     */
    String getFileName();

    /**
     * Gets the form nme of the field which contains the file.
     */
    String fieldName();

    /**
     * Gets the raw Content-Disposition header of the uploaded file.
     */
    String getContentDisposition();

    /**
     * Gets the header dictionary of the uploaded file.
     */
    HeaderCollection getHeaders();

    /**
     * Gets the file length in bytes.
     */
    long getLength();

    /**
     * Gets the request stream for reading the uploaded file.
     */
    InputStream getInputStream() throws IOException;

    /**
     * Copies the contents of the uploaded file to the {@param targetStream}.
     */
    void copyTo(OutputStream targetStream);

    String getExtension();
}
