package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MappingPatternBuilderTest {

    @ParameterizedTest
    @MethodSource("MappingAndPattern")
    void getSimpleMappingPattern(String mapping, String pattern) {
        MappingPatternBuilder builder = new MappingPatternBuilder();


        assertEquals(pattern, builder.getPattern(mapping));
    }

    static String[][] MappingAndPattern() {
        return new String[][]{
                new String[] {"", ""},
                new String[] {"posts", "posts"},
                new String[] {"posts/{postId}", "posts/([\\w-]+)"},
                new String[] {"posts/{postId}/comments/{commentId}", "posts/([\\w-]+)/comments/([\\w-]+)"},

                new String[] {"responses/{responseID}.{length}-{id}", "responses/([\\w-]+).([\\w-]+)-([\\w-]+)"}
        };
    }
}