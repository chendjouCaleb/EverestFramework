package Everest.Framework.Mvc.ActionResultExecutor;


import Everest.Framework.Core.Exception.InputOutputException;
import Everest.Framework.Core.FileUtils;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Mvc.Result.LocalFileResult;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Executes the {@link LocalFileResult} result.
 * @see LocalFileResult
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class LocalFileResultExecutor implements IResultExecutor<LocalFileResult> {
    private static final int BUFFER_SIZE = 1024;

    public void execute(HttpContext httpContext, LocalFileResult result) {
        HttpResponse httpResponse = httpContext.getResponse();

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(result.getFile()), BUFFER_SIZE);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpResponse.outputStream(), BUFFER_SIZE);
        ) {


            String mimeType = FileUtils.getMimeType(bufferedInputStream);

            httpResponse.setContentType(mimeType);
            httpResponse.addHeader("Content-Length", String.valueOf(result.getFile().length()));
            httpResponse.addHeader("Content-Disposition", "inline; filename=\"" + result.getFile().getName() + "\"");

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;

            while ((length = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }
}
