package Everest.Framework.Mvc.Action;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Binding.BindingState;
import Everest.Framework.Mvc.Routing.RouteDescriptor;
import Everest.Framework.Mvc.Routing.RouteValues;

import javax.annotation.Nonnull;

/**
 * Context object for execution of action which has been selected as part of an HTTP request.
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ActionContext {

    public ActionContext() {}
    public ActionContext(@Nonnull HttpContext httpContext){
        this.httpContext = httpContext;
    }

    public ActionContext(@Nonnull HttpContext httpContext, @Nonnull ActionDescriptor actionDescriptor, @Nonnull RouteValues routeValues, @Nonnull BindingState bindingState) {
        this.httpContext = httpContext;
        this.actionDescriptor = actionDescriptor;
        this.routeValues = routeValues;
        this.bindingState = bindingState;
    }

    public ActionContext(@Nonnull ActionContext actionContext){
        this.httpContext = actionContext.getHttpContext();
        this.actionDescriptor = actionContext.getActionDescriptor();
        this.routeValues = actionContext.getRouteValues();
        this.bindingState = actionContext.bindingState;
    }

    public void setActionContext(@Nonnull ActionContext actionContext) {
        this.httpContext = actionContext.getHttpContext();
        this.actionDescriptor = actionContext.getActionDescriptor();
        this.routeValues = actionContext.getRouteValues();
        this.bindingState = actionContext.bindingState;
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
     * the {@link RouteValues} for the current request.
     */
    private RouteValues routeValues;

    private ControllerDescriptor controllerDescriptor;

    private RouteDescriptor routeDescriptor;

    /**
     * The request data state.
     */
    private BindingState bindingState;

    /**
     * An instance of the controller class of the action.
     */
    private Object controllerInstance;

    /**
     * The object returned by the execution of an action method.
     */
    private Object executionResult;

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

    public RouteValues getRouteValues() {
        return routeValues;
    }

    public void setRouteValues(@Nonnull RouteValues routeValues) {
        this.routeValues = routeValues;
    }

    public BindingState getBindingState() {
        return bindingState;
    }

    public void setBindingState(BindingState bindingState) {
        this.bindingState = bindingState;
    }

    public ControllerDescriptor getControllerDescriptor() {
        return controllerDescriptor;
    }

    public ActionContext setControllerDescriptor(ControllerDescriptor controllerDescriptor) {
        this.controllerDescriptor = controllerDescriptor;
        return this;
    }

    public RouteDescriptor getRouteDescriptor() {
        return routeDescriptor;
    }

    public ActionContext setRouteDescriptor(RouteDescriptor routeDescriptor) {
        this.routeDescriptor = routeDescriptor;
        return this;
    }

    public Object getControllerInstance() {
        return controllerInstance;
    }

    public ActionContext setControllerInstance(Object controllerInstance) {
        this.controllerInstance = controllerInstance;
        return this;
    }

    public Object getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(Object executionResult) {
        this.executionResult = executionResult;
    }
}
