package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Core.Exception.InputOutputException;
import Everest.Framework.Core.StringUtils;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpRequest;
import Everest.Framework.Mvc.Service.JsonConverter;
import Everest.Framework.Mvc.ValueResolver.Annotations.BodyValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;

/**
 * Resolver to Converter the request body to a target class.
 * Only Json body can be handled for the moment.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class BodyValueResolver implements IAnnotationValueResolver<BodyValue> {
    private Logger logger = LoggerFactory.getLogger(BodyValueResolver.class);
    private JsonConverter converter;

    public BodyValueResolver(JsonConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, BodyValue annotation) {

        String body = getStringBody(httpContext.getRequest());
        logger.info("body string: [{}]", body);

        Object object = converter.toObject(body, parameter.getType());
        logger.info("Body object: {}", object);
        return  object;
    }

    private String getStringBody(HttpRequest httpRequest){
        BufferedReader reader = httpRequest.reader();
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(StringUtils.uriDecode(line, Charset.forName("UTF-8")));
            }
            reader.close();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }

        return builder.toString();
    }
}
