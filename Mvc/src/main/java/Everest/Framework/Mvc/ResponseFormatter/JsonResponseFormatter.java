package Everest.Framework.Mvc.ResponseFormatter;

import Everest.Framework.Http.MediaType;
import Everest.Framework.Mvc.Service.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonResponseFormatter implements IResponseFormatter {
    private Logger logger = LoggerFactory.getLogger(JsonResponseFormatter.class);
    private JsonConverter jsonConverter;

    public JsonResponseFormatter(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public String[] getMediaTypes() {
        return new String[]{
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE
        };
    }


    public void writeResponseBody(ResponseFormatContext context) {
        logger.info("Response object: {}", context.getObject());
        String response = jsonConverter.toJSON(context.getObject());
        logger.info("Json response: {}", response);
        context.getHttpContext().getResponse().write(response);
    }
}
