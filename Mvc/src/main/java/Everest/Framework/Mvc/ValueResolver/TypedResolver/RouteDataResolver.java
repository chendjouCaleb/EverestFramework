package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Routing.RouteValues;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;


import java.lang.reflect.Parameter;

/**
 * Resolves all the route data of the request.
 * @see RouteValues
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteDataResolver implements ITypedValueResolver<RouteValues> {

    public RouteValues getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getRouteValues();
    }
}
