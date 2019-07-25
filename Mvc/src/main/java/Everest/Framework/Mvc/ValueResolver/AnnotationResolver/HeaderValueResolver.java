package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.HeaderValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Parameter;

/**
 * Resolves parameter by an header parameter value.
 *
 *  * if the name of the header is not provided by annotation,
 *  * the method parameter name is used as header name.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class HeaderValueResolver implements IAnnotationValueResolver<HeaderValue> {

    public Object getVariable(ActionContext actionContext, Parameter parameter, HeaderValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        String stringVal = actionContext.getHttpContext().getRequest().headers().getHeader(name);

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
