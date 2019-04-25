package Everest.Framework.Mvc.Routing;

import Everest.Framework.Mvc.Action.ActionDescriptor;
import Everest.Framework.Mvc.Action.ControllerDescriptor;
import Everest.Framework.Mvc.Mapping.HttpMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * This class parse controller class, identifies action method, extract route description and store them.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteLoader {
    private List<RouteDescriptor> routeDescriptors = new ArrayList<>();
    private List<ControllerDescriptor> controllerDescriptors = new ArrayList<>();
    private List<ActionDescriptor> actionDescriptors = new ArrayList<>();


}
