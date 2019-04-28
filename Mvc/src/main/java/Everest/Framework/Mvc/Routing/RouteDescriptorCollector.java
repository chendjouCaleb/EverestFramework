package Everest.Framework.Mvc.Routing;

import Everest.Framework.Mvc.Action.ActionDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class parse controller class, identifies action method, extract route description and store them.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
public class RouteDescriptorCollector{

    private RouteDescriptorLoader routeDescriptorLoader;

    public RouteDescriptorCollector(RouteDescriptorLoader routeDescriptorLoader) {
        this.routeDescriptorLoader = routeDescriptorLoader;
    }

    /**
     * Collects {@link RouteDescriptor} from a list of {@link ActionDescriptor}.
     * @param actionDescriptors {@link ActionDescriptor} list which contains descriptions of all action methods.
     * We assume {@param actionDescriptors } was loaded by {@link Everest.Framework.Mvc.Action.ActionDescriptorLoader}.
     */
    public List<RouteDescriptor> collect(List<ActionDescriptor> actionDescriptors) {
        List<RouteDescriptor> descriptors = new ArrayList<>();
        for (ActionDescriptor actionDescriptor: actionDescriptors){
            RouteDescriptor routeDescriptor = routeDescriptorLoader.loadRouteDescriptor(actionDescriptor);
            descriptors.add(routeDescriptor);
        }
        return descriptors;
    }




}
