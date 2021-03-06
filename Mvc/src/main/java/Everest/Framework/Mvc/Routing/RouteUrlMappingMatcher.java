package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;

import javax.annotation.Nonnull;

/**
 * This checks that the url of an incoming request matches the mapping of a {@link RouteDescriptor} ;
 *  The method {@link RouteUrlMappingMatcher#matches(RouteDescriptor, String)} assumes that the provided {@link RouteDescriptor}
 *  have an URL mapping and a mapping regex pattern.
 *
 *  Also, we assume that the url doesn't contains the query fragment.
 *  So the url 'posts/15?commented=false' doesn't matches the 'posts/{postId}' mapping.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
@Singleton
public class RouteUrlMappingMatcher {

    /**
     * Checks that {@param url} matches the mapping of {@param descriptor}.
     * For the moment, we just make sure that the url matches the regex pattern of descriptor.
     * @param descriptor The route descriptor
     * @param url An http request url.
     * @return {@code true} if {@param url} matches the mapping of {@param descriptor}.
     */
    public boolean matches(@Nonnull RouteDescriptor descriptor, @Nonnull String url){
        if(url.startsWith("/")){
            url = url.substring(1);
        }
        return url.matches(descriptor.getMappingPattern());
    }
}
