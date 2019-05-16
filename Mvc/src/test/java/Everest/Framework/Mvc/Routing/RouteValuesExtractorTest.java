package Everest.Framework.Mvc.Routing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteValuesExtractorTest {
    private RouteValuesExtractor valueExtractor = new RouteValuesExtractor();
    private MappingPatternBuilder mappingPatternBuilder = new MappingPatternBuilder();
    private RouteParameterExtractor parameterExtractor = new RouteParameterExtractor();
    @Test
    void extract() {
        String mapping = "{param1}-abc-{param2}/{param3}/{param4}:{param5}/{param6}/e-{param7}";
        RouteParameters parameters = parameterExtractor.extractParameter(mapping);


        RouteDescriptor descriptor = new RouteDescriptor();
        descriptor.setMapping(mapping);
        descriptor.setParameters(parameters);
        descriptor.setMappingPattern(mappingPatternBuilder.getPattern(mapping));

        RouteValues values = valueExtractor.extract("abc-abc-12/param3/15:50/60/e-abc", descriptor);
        System.out.println(values);

        assertEquals("abc", values.get("param1"));
        assertEquals("12", values.get("param2"));
        assertEquals("param3", values.get("param3"));
        assertEquals("15", values.get("param4"));
        assertEquals("50", values.get("param5"));
        assertEquals("60", values.get("param6"));
        assertEquals("abc", values.get("param7"));
    }
}