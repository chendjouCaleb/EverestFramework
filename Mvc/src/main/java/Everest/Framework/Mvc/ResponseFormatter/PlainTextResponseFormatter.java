package Everest.Framework.Mvc.ResponseFormatter;

import Everest.Framework.Http.MediaType;

public class PlainTextResponseFormatter implements IResponseFormatter {
    public String[] getMediaTypes() {
        return new String[]{MediaType.TEXT_PLAIN_VALUE};
    }


    public void writeResponseBody(ResponseFormatContext context) {
        String response = context.getObject().toString();
        context.getHttpContext().getResponse().write(response);
    }
}
