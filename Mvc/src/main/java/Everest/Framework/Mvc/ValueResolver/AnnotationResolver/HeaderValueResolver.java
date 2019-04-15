package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.HeaderValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Parameter;

/**
 * Resolves parameter by an header parameter value.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class HeaderValueResolver implements IAnnotationValueResolver<HeaderValue> {

    public Object getVariable(HttpContext httpContext, Parameter parameter, HeaderValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        String stringVal = httpContext.getRequest().headers().get(name);

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
