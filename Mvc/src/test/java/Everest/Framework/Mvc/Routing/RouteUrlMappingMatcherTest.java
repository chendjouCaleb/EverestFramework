package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteUrlMappingMatcherTest {
    private MappingPatternBuilder mappingPatternBuilder = new MappingPatternBuilder();
    private RouteUrlMappingMatcher routeUrlMappingMatcher = new RouteUrlMappingMatcher();

    @ParameterizedTest
    @MethodSource("simpleUrlAndMapping_Success")
    void matchSimpleUrl_Success(String mapping, String url) {
        RouteDescriptor descriptor = new RouteDescriptor();

        String mappingPattern = mappingPatternBuilder.getPattern(mapping);
        descriptor.setMappingPattern(mappingPattern);

        assertTrue(routeUrlMappingMatcher.match(descriptor, url));
    }

    @ParameterizedTest
    @MethodSource("simpleUrlAndMapping_Fail")
    void matchSimpleUrl_Fail(String mapping, String url) {
        RouteDescriptor descriptor = new RouteDescriptor();

        String mappingPattern = mappingPatternBuilder.getPattern(mapping);
        descriptor.setMappingPattern(mappingPattern);

        assertFalse(routeUrlMappingMatcher.match(descriptor, url));
    }

    static String[][] simpleUrlAndMapping_Success() {
        return new String[][]{
                new String[] {"", ""},
                new String[] {"posts", "posts"},
                new String[] {"posts/{postId}", "posts/15"},
                new String[] {"posts/{postId}/comments/{commentId}", "posts/15/comments/2"},
                new String[] {"responses/{responseID}.{length}-{id}", "response/10.15-12"}
        };
    }

    static String[][] simpleUrlAndMapping_Fail() {
        return new String[][]{
                new String[] {"", "1"},
                new String[] {"post", "posts"},
                new String[] {"posts/{postId}", "posts"},
                new String[] {"post/{postId}", "posts/15"},
                new String[] {"posts/{postId}/comments", "posts/15/comments/2"},
        };
    }
}