package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.Action.ActionDescriptor;

/**
 * This class builds the {@link RouteDescriptor} of an {@link Everest.Framework.Mvc.Action.ActionDescriptor}.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
@Singleton
public class RouteDescriptorBuilder {

    private MappingPatternBuilder mappingPatternBuilder;
    private RouteParameterExtractor routeParameterExtractor;

    public RouteDescriptorBuilder(MappingPatternBuilder mappingPatternBuilder, RouteParameterExtractor routeParameterExtractor) {
        this.mappingPatternBuilder = mappingPatternBuilder;
        this.routeParameterExtractor = routeParameterExtractor;
    }

    public RouteDescriptor loadRouteDescriptor(ActionDescriptor actionDescriptor) {
        if (actionDescriptor == null) {
            throw new InvalidOperationException("Cannot load route from null action description");
        }

        if (actionDescriptor.getControllerDescriptor() == null) {
            throw new InvalidOperationException("Cannot load route from action: " +
                    actionDescriptor.getMethod().getName() + " has null controller descriptor")
                    ;
        }

        RouteDescriptor routeDescriptor = new RouteDescriptor();
        routeDescriptor.setActionDescriptor(actionDescriptor);
        String controllerMapping = actionDescriptor.getControllerDescriptor().getMapping().trim();
        String actionMapping = actionDescriptor.getMapping();
        String mapping;

        if(!"".equals(controllerMapping) && ! "".equals(actionMapping)){
            mapping = controllerMapping + "/" + actionMapping;
        }else{
            mapping = controllerMapping + actionMapping;
        }

        routeDescriptor.setMapping(mapping);

        routeDescriptor.setMappingPattern(mappingPatternBuilder.getPattern(mapping));

        routeDescriptor.setParameters(routeParameterExtractor.extractParameter(mapping));

        return routeDescriptor;
    }
}
