package Everest.Framework.Mvc.Action;

import Everest.Framework.Http.HttpMethod;

import java.lang.reflect.Method;

/**
 * Describe an method action.
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ActionDescriptor {

    /**
     * The url mapping. Ex: /post/{postId}
     */
    private String mapping;

    /**
     * The action method.
     */
    private Method method;

    /**
     * The parent controller descriptor.
     */
    private ControllerDescriptor controllerDescriptor;

    /**
     * The http method of the action.
     */
    private HttpMethod verbs;

    public ActionDescriptor() {
    }

    public ActionDescriptor(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ActionDescriptor(Method method, ControllerDescriptor controllerDescriptor) {
        this.method = method;
        this.controllerDescriptor = controllerDescriptor;
    }

    public ControllerDescriptor getControllerDescriptor() {
        return controllerDescriptor;
    }

    public void setControllerDescriptor(ControllerDescriptor controllerDescriptor) {
        this.controllerDescriptor = controllerDescriptor;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public HttpMethod getVerbs() {
        return verbs;
    }

    public void setVerbs(HttpMethod verbs) {
        this.verbs = verbs;
    }
}
