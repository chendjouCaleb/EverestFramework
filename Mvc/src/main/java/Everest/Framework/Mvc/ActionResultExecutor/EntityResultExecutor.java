package Everest.Framework.Mvc.ActionResultExecutor;


import Everest.Framework.Core.StringUtils;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Mvc.ResponseFormatter.ResponseFormatContext;
import Everest.Framework.Mvc.ResponseFormatter.ResponseFormatter;
import Everest.Framework.Mvc.Result.EntityResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to execute an eventual {@link EntityResult} returned by an action method execution.
 * The executor generate an send the HTTP Response based on the received result.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class EntityResultExecutor implements IResultExecutor<EntityResult> {
    private Logger logger = LoggerFactory.getLogger(EntityResultExecutor.class);
    private ResponseFormatter formatter;

    public EntityResultExecutor(ResponseFormatter formatter) {
        this.formatter = formatter;
    }

    public void execute(HttpContext context, EntityResult result) {
        HttpResponse response = context.getResponse();
        if(StringUtils.isEmpty(result.getContentType())){
            result.setContentType(context.getOptions().getResponseContentType());
        }

        response.setContentType(result.getContentType());

        if(result.getStatusCode() != 0){
            response.setStatusCode(result.getStatusCode());
        }

        logger.info("Response: [content-type={}", response.contentType());
        formatter.format(new ResponseFormatContext(context, result.getBody()));
    }
}
