package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.Action.ActionDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class parse controller class, identifies action method, extract route description and store them.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
@Singleton
public class RouteDescriptorStore {
    private Logger logger = LoggerFactory.getLogger(RouteDescriptorStore.class);
    private RouteDescriptorBuilder routeDescriptorBuilder;
    private List<RouteDescriptor> routeDescriptors = new ArrayList<>();

    public RouteDescriptorStore(RouteDescriptorBuilder routeDescriptorBuilder) {
        this.routeDescriptorBuilder = routeDescriptorBuilder;
    }

    /**
     * Collects {@link RouteDescriptor} from a list of {@link ActionDescriptor}.
     * @param actionDescriptors {@link ActionDescriptor} list which contains descriptions of all action methods.
     * We assume {@param actionDescriptors } was loaded by {@link Everest.Framework.Mvc.Action.ActionDescriptorLoader}.
     */
    public List<RouteDescriptor> collect(List<ActionDescriptor> actionDescriptors) {
        for (ActionDescriptor actionDescriptor: actionDescriptors){
            RouteDescriptor routeDescriptor = routeDescriptorBuilder.loadRouteDescriptor(actionDescriptor);
            routeDescriptors.add(routeDescriptor);
            logger.info("Action Method: [class: {}, method: {}, mapping: {}, HTTP verbs: {}]",
                    routeDescriptor.getActionDescriptor().getControllerDescriptor().getClassType().getSimpleName(),
                    routeDescriptor.getActionDescriptor().getMethod().getName(),
                    routeDescriptor.getMapping(),
                    routeDescriptor.getActionDescriptor().getVerbs());

        }
        return routeDescriptors;
    }


    public List<RouteDescriptor> getRouteDescriptors() {
        return routeDescriptors;
    }
}
