package Everest.Framework.Mvc.Routing;

import Everest.Framework.Mvc.Action.ActionDescriptor;

/**
 * The description of an mapped http route.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteDescriptor {

    /**
     * The url mapping. Ex: /post/{postId}.
     * Must be actionDescriptor mapping + controllerDescriptor mapping.
     */
    private String mapping;

    /**
     * Represents the url mapping transformed into a java Regexp.
     */
    private String mappingPattern;

    /**
     * The  parameters found in the urlMapping;
     */
    private RouteParameters parameters = new RouteParameters();

    /**
     * The {@link ActionDescriptor} of the route.
     */
    private ActionDescriptor actionDescriptor;


    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }


    public ActionDescriptor getActionDescriptor() {
        return actionDescriptor;
    }

    public void setActionDescriptor(ActionDescriptor actionDescriptor) {
        this.actionDescriptor = actionDescriptor;
    }

    public String getMappingPattern() {
        return mappingPattern;
    }

    public void setMappingPattern(String mappingPattern) {
        this.mappingPattern = mappingPattern;
    }

    public RouteParameters getParameters() {
        return parameters;
    }

    public void setParameters(RouteParameters parameters) {
        this.parameters = parameters;
    }
}
