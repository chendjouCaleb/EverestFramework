package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MappingPatternBuilderTest {

    @Test
    void getSimpleMappingPattern() {
        MappingPatternBuilder builder = new MappingPatternBuilder();
        String mapping1 = "/posts/{postId}";
        String pattern1 = "/posts/([\\w]+)";
        String mapping2 = "/posts";
        String pattern2 = "/posts";

        assertEquals(pattern1, builder.getPattern(mapping1));
        assertEquals(pattern2, builder.getPattern(mapping2));

    }
}