package Everest.Framework.Mvc.ResponseFormatter;

import Everest.Framework.Http.DefaultHttpContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainTextResponseFormatterTest {
    PlainTextResponseFormatter formatter = new PlainTextResponseFormatter();
    @Test
    void writeResponseBody() {
        DefaultHttpContext httpContext = new DefaultHttpContext();

        String result = "plain text result";
        ResponseFormatContext formatContext = new ResponseFormatContext(httpContext, result);

        formatter.writeResponseBody(formatContext);

        assertEquals(result, httpContext.getResponse().writer().toString());
    }
}