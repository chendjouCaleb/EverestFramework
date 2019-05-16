package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteParameterExtractorTest {

    @Test
    void getNames() {
        RouteParameterExtractor extractor = new RouteParameterExtractor();
        String mapping = "posts/{postId}/comments-{commentId}";
        RouteParameters parameters = extractor.extractParameter(mapping);

        assertEquals(2, parameters.size());
        assertEquals(0, parameters.findByName("postId").getPosition());
        assertEquals(1, parameters.findByName("commentId").getPosition());

        System.out.println(parameters.toString());
    }
}