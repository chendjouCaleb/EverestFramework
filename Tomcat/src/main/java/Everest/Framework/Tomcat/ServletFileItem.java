package Everest.Framework.Tomcat;

import Everest.Framework.Core.Exception.InputOutputException;
import Everest.Framework.Http.HeaderCollection;
import Everest.Framework.Http.IFormFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServletFileItem implements IFormFile {
    private FileItem fileItem;
    private HeaderCollection headers = new HeaderCollection();

    public ServletFileItem(FileItem fileItem) {
        this.fileItem = fileItem;

        FileItemHeaders fileItemHeaders = fileItem.getHeaders();
//        String headerName;
//        while (fileItemHeaders.getHeaderNames().hasNext()){
//            headerName = fileItemHeaders.getHeaderNames().next();
//            fileItemHeaders.getHeaderNames().remove();
//            headers.setHeaders(headerName, fileItemHeaders.getHeaders(headerName));
//
//        }

    }

    @Override
    public String getContentType() {
        return fileItem.getContentType();
    }

    @Override
    public String getFileName() {
        return fileItem.getFieldName();
    }

    @Override
    public String fieldName() {
        return fileItem.getFieldName();
    }

    @Override
    public String getContentDisposition() {
        return null;
    }

    @Override
    public HeaderCollection getHeaders() {
        return headers;
    }

    @Override
    public long getLength() {
        return fileItem.getSize();
    }

    @Override
    public InputStream getInputStream(){
        try {
            return fileItem.getInputStream();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public void copyTo(OutputStream targetStream) {
        try {
            IOUtils.copy(getInputStream(), targetStream);
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public String getExtension() {
        return FilenameUtils.getExtension(fileItem.getName());
    }
}
