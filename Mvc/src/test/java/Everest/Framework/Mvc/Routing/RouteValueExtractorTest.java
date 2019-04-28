package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.api.Test;

class RouteValueExtractorTest {

    private RouteValueExtractor routeValueExtractor = new RouteValueExtractor();
    private RouteParameterExtractor routeParameterExtractor = new RouteParameterExtractor();
    private MappingPatternBuilder mappingPatternBuilder = new MappingPatternBuilder();
    @Test
    void extract() {
        String mapping = "posts/{postId}/comments/{commentId}";
        RouteDescriptor descriptor = new RouteDescriptor();
        descriptor.setMapping(mapping);
        descriptor.setParameters(routeParameterExtractor.getNames(mapping));
        descriptor.setMappingPattern(mappingPatternBuilder.getPattern(mapping));

        RouteValues values = routeValueExtractor.extract(descriptor, "posts/118/comments/10");

        System.out.println(values.toString());
    }
}