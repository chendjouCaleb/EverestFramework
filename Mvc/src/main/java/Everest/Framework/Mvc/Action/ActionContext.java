package Everest.Framework.Mvc.Action;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Binding.BindingState;
import Everest.Framework.Mvc.Routing.RouteData;

import javax.annotation.Nonnull;

/**
 * Context object for execution of action which has been selected as part of an HTTP request.
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ActionContext {

    public ActionContext(@Nonnull HttpContext httpContext, @Nonnull ActionDescriptor actionDescriptor, @Nonnull RouteData routeData, @Nonnull BindingState bindingState) {
        this.httpContext = httpContext;
        this.actionDescriptor = actionDescriptor;
        this.routeData = routeData;
        this.bindingState = bindingState;
    }

    /**
     * The {@link HttpContext} for the current request.
     */
    private HttpContext httpContext;

    /**
     * The {@link ActionDescriptor} of the selected action method.
     */
    private ActionDescriptor actionDescriptor;

    /**
     * the {@link RouteData} for the current request.
     */
    private RouteData routeData;

    /**
     * The request data state.
     */
    private BindingState bindingState;

    public HttpContext getHttpContext() {
        return httpContext;
    }

    public void setHttpContext(@Nonnull HttpContext httpContext) {
        this.httpContext = httpContext;
    }

    public ActionDescriptor getActionDescriptor() {
        return actionDescriptor;
    }

    public void setActionDescriptor(@Nonnull ActionDescriptor actionDescriptor) {
        this.actionDescriptor = actionDescriptor;
    }

    public RouteData getRouteData() {
        return routeData;
    }

    public void setRouteData(@Nonnull RouteData routeData) {
        this.routeData = routeData;
    }

    public BindingState getBindingState() {
        return bindingState;
    }

    public void setBindingState(BindingState bindingState) {
        this.bindingState = bindingState;
    }
}
