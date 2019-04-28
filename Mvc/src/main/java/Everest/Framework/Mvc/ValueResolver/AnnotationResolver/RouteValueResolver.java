package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Routing.RouteValues;
import Everest.Framework.Mvc.ValueResolver.Annotations.RouteValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;

/**
 * Value resolver for the {@link RouteValue} annotated parameters.
 * Provides one route value by the route parameter name.
 *
 * if the name of the route parameter is not provided,
 * the method parameter name is used as route parameter name.
 *
 * @see RouteValues
 * @see RouteValue
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteValueResolver implements IAnnotationValueResolver<RouteValue> {
    private Logger logger = LoggerFactory.getLogger(RouteValueResolver.class);
    public Object getVariable(ActionContext actionContext, Parameter parameter, RouteValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }
        return actionContext.getRouteValues().get(name, parameter.getType());
    }
}
