package Everest.Framework.Mvc.Routing;

import Everest.Framework.Http.HttpMethod;
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
}
