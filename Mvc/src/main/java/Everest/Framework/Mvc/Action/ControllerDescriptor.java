package Everest.Framework.Mvc.Action;


import java.util.ArrayList;
import java.util.List;

/**
 * The description of an HTTP controller.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ControllerDescriptor {

    /**
     * The url mapping. Ex: /post/{postId}
     */
    private String mapping;

    /**
     * The name of the controller. The default name must be name of the controller class minus the world 'controller'.
     */
    private String name;

    /**
     * The controller instance.
     */
    private Object object;

    /**
     * The descriptors of action methods present in the controller.
     */
    private List<ActionDescriptor> actionDescriptors = new ArrayList<>();

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<ActionDescriptor> getActionDescriptors() {
        return actionDescriptors;
    }

    public void setActionDescriptors(List<ActionDescriptor> actionDescriptors) {
        this.actionDescriptors = actionDescriptors;
    }
}
