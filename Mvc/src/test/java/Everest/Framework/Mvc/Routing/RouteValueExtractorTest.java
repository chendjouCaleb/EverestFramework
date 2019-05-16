package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.api.Test;

class RouteValueExtractorTest {

    private RouteValuesExtractor routeValueExtractor = new RouteValuesExtractor();
    private RouteParameterExtractor routeParameterExtractor = new RouteParameterExtractor();
    private MappingPatternBuilder mappingPatternBuilder = new MappingPatternBuilder();
    @Test
    void extract() {
        String mapping = "posts/{postId}/comments/{commentId}";
        RouteDescriptor descriptor = new RouteDescriptor();
        descriptor.setMapping(mapping);
        descriptor.setParameters(routeParameterExtractor.extractParameter(mapping));
        descriptor.setMappingPattern(mappingPatternBuilder.getPattern(mapping));

        RouteValues values = routeValueExtractor.extract( "posts/118/comments/10", descriptor);

        System.out.println(values.toString());
    }
}