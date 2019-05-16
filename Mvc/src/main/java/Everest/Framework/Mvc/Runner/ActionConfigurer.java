package Everest.Framework.Mvc.Runner;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.Action.ActionDescriptorStore;
import Everest.Framework.Mvc.Filter.ActionMethodFilterDescriptorBuilder;
import Everest.Framework.Mvc.Filter.FilterDescriptorCollection;
import Everest.Framework.Mvc.Routing.RouteDescriptorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class ActionConfigurer {
    private ActionDescriptorStore actionDescriptorStore;
    private RouteDescriptorStore routeDescriptors;
    private ActionMethodFilterDescriptorBuilder filterDescriptorBuilder;
    private Logger logger = LoggerFactory.getLogger(ActionConfigurer.class);

    public ActionConfigurer(ActionDescriptorStore actionDescriptorStore, RouteDescriptorStore routeDescriptors, ActionMethodFilterDescriptorBuilder filterDescriptorBuilder) {
        this.actionDescriptorStore = actionDescriptorStore;
        this.routeDescriptors = routeDescriptors;
        this.filterDescriptorBuilder = filterDescriptorBuilder;
    }

    public void collectAction(List<Class<?>> types) {
        actionDescriptorStore.setControllerClasses(types);
        actionDescriptorStore.collect();
        routeDescriptors.collect(actionDescriptorStore.getActionDescriptors());

        routeDescriptors.getRouteDescriptors().forEach(descriptor -> {
            FilterDescriptorCollection filters = filterDescriptorBuilder.getFiltersDescriptors(
                    descriptor.getActionDescriptor().getControllerDescriptor().getClassType(),
                    descriptor.getActionDescriptor().getMethod());

            descriptor.setActionFilters(filters);
        });
    }
}
