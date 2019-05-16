package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;

import javax.annotation.Nonnull;

/**
 * The role of this class is to select the best route of an url of an incoming HTTP request.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */
@Singleton
public class RouteDispatcher {
    private RouteDescriptorStore routeDescriptors;
    private RouteUrlMappingMatcher urlMappingMatcher;

    public RouteDispatcher(RouteDescriptorStore routeDescriptors, RouteUrlMappingMatcher urlMappingMatcher) {
        this.routeDescriptors = routeDescriptors;
        this.urlMappingMatcher = urlMappingMatcher;
    }

    /**
     * Selects a route which the mapping matches the provided url and verb is same that the specified verbs.
     * @param url The url of the request.
     * @param verb The HTTP method of the request.
     * @return The best route which matches the url and verbs.
     * @throws MultipleActionMatcherException If there many route which the url and verbs.
     */
    public RouteDescriptor selectRoute(@Nonnull String url, @Nonnull String verb) {
        RouteDescriptor selectedDescriptor = null;
        for (RouteDescriptor descriptor: routeDescriptors.getRouteDescriptors()){
            if(urlMappingMatcher.matches(descriptor, url)
                    && descriptor.getActionDescriptor().getVerbs().toString().equals(verb)){

                if(selectedDescriptor != null){
                    throw new MultipleActionMatcherException(
                            String.format("The url '%s' match multiple action '%s' and '%s'.",
                                    url, descriptor.getActionDescriptor().getMethod(), selectedDescriptor
                                            .getActionDescriptor().getMethod())
                    );
                }
                selectedDescriptor = descriptor;
            }
        }
        if(selectedDescriptor == null) {
            throw new RouteNotFoundException(String.format("There are no route for url '%s' nd http method '%s'", url, verb));
        }
        return selectedDescriptor;
    }
}
