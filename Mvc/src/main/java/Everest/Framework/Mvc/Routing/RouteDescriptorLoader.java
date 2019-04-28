package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Mvc.Action.ActionDescriptor;
import org.apache.commons.lang3.StringUtils;

/**
 * This class builds the {@link RouteDescriptor} of an {@link Everest.Framework.Mvc.Action.ActionDescriptor}.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
public class RouteDescriptorLoader {

    private MappingPatternBuilder mappingPatternBuilder;
    private RouteParameterExtractor routeParameterExtractor;

    public RouteDescriptorLoader(MappingPatternBuilder mappingPatternBuilder, RouteParameterExtractor routeParameterExtractor) {
        this.mappingPatternBuilder = mappingPatternBuilder;
        this.routeParameterExtractor = routeParameterExtractor;
    }

    public RouteDescriptor loadRouteDescriptor(ActionDescriptor descriptor) {
        if (descriptor == null) {
            throw new InvalidOperationException("Cannot load route from null action description");
        }

        if (descriptor.getControllerDescriptor() == null) {
            throw new InvalidOperationException("Cannot load route from action: " +
                    descriptor.getMethod().getName() + " has null controller descriptor")
                    ;
        }

        RouteDescriptor routeDescriptor = new RouteDescriptor();
        routeDescriptor.setActionDescriptor(descriptor);
        String mapping = descriptor.getControllerDescriptor().getMapping().trim();
        if (StringUtils.isEmpty(mapping)) {
            mapping = descriptor.getMapping();
        } else {
            mapping += "/" + descriptor.getMapping().trim();
        }
        routeDescriptor.setMapping(mapping);

        routeDescriptor.setMappingPattern(mappingPatternBuilder.getPattern(mapping));

        routeDescriptor.setParameters(routeParameterExtractor.getNames(mapping));

        return routeDescriptor;
    }
}
