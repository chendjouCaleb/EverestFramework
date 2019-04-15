package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Routing.RouteData;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;


import java.lang.reflect.Parameter;

/**
 * Resolves all the route data of the request.
 * @see RouteData
 */
public class RouteDataResolver implements ITypedValueResolver<RouteData> {

    public RouteData getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getRouteData();
    }
}
