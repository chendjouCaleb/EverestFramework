package Everest.Framework.Http;

import Everest.Framework.Core.Exception.InputOutputException;
import Everest.Framework.Core.FileUtils;

import java.io.*;

/**
 * The default implementation of {@link IFormFile}.
 * This implementation is useful for test purpose.
 */
public class FormFile implements IFormFile{
    private FileInputStream inputStream;
    private File file;
    private String contentType;
    private long contentLength;

    public FormFile(File file) {
        this.file = file;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public String getContentType() {
        return FileUtils.getMimeType(file);
    }

    public String getExtension() {
        return FileUtils.getExtension(file);
    }

    @Override
    public String getFileName() {
        return file.getName();
    }

    @Override
    public String fieldName() {
        return null;
    }

    @Override
    public String getContentDisposition() {
        return "";
    }

    @Override
    public HeaderCollection getHeaders() {
        return new HeaderCollection();
    }

    @Override
    public long getLength() {
        return file.length();
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void copyTo(OutputStream targetStream) {

    }
}
