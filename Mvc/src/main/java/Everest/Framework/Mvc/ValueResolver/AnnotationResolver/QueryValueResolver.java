package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;


import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.QueryValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.List;

/**
 * Provides a value of the request query.
 * if the name of the query parameter is not provided by annotation,
 * the method parameter name is used as query parameter name.
 *
 * @see Everest.Framework.Http.QueryCollection
 * @see QueryValue
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class QueryValueResolver implements IAnnotationValueResolver<QueryValue> {
    private Logger logger = LoggerFactory.getLogger(QueryValueResolver.class);
    public Object getVariable(ActionContext actionContext, Parameter parameter, QueryValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        List<String> stringVal = actionContext.getHttpContext().getRequest().query().get(name);


        logger.info("Query value: {} = {}", name, stringVal);

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}