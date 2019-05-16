package Everest.Framework.Mvc.ResponseFormatter;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import org.apache.commons.lang3.StringUtils;


@Singleton
public class ResponseFormatter {
    private ResponseFormatterSelector formatterSelector;

    public ResponseFormatter(ResponseFormatterSelector formatterSelector) {
        this.formatterSelector = formatterSelector;
    }

    public void format(ResponseFormatContext context){
        HttpContext httpContext = context.getHttpContext();
        HttpResponse response = httpContext.getResponse();

        String contentType = response.contentType();
        if(StringUtils.isEmpty(contentType)){
            contentType = httpContext.getOptions().getResponseContentType();
        }

        IResponseFormatter formatter = formatterSelector.getFormatter(contentType);

        formatter.writeResponseBody(context);
    }
}
