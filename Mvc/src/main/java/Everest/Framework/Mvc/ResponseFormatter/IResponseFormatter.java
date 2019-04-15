package Everest.Framework.Mvc.ResponseFormatter;


public interface IResponseFormatter {
    String[] getMediaTypes();
    void writeResponseBody(ResponseFormatContext context);
}
