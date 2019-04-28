package Everest.Framework.Mvc.Routing;

import Everest.Framework.Mvc.Action.ActionDescriptor;
import Everest.Framework.Mvc.Action.ActionDescriptorLoader;
import Everest.Framework.Mvc.Action.ControllerDescriptor;
import Everest.Framework.Mvc.Action.ControllerDescriptorLoader;
import Everest.Framework.Mvc.Mapping.DeleteMapping;
import Everest.Framework.Mvc.Mapping.GetMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingFor.MappingGetter;
import Everest.Framework.Mvc.Mapping.PostMapping;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class RouteDescriptorLoaderTest {

    private ControllerDescriptorLoader controllerDescriptorLoader = new ControllerDescriptorLoader();
    private ActionDescriptorLoader actionDescriptorLoader = new ActionDescriptorLoader(new MappingGetter());
    private MappingPatternBuilder mappingPatternBuilder = new MappingPatternBuilder();
    private RouteParameterExtractor routeParameterExtractor = new RouteParameterExtractor();
    private RouteDescriptorLoader loader =
            new RouteDescriptorLoader(mappingPatternBuilder, routeParameterExtractor);
    @Test
    void collect() {
    }

    @Test
    void loadSimpleRouteDescriptor() {
        ControllerDescriptor controllerDescriptor =
                controllerDescriptorLoader.LoadControllerDescriptor(StarController.class);

        Method method = MethodUtils.getMatchingMethod(StarController.class, "Get");
        ActionDescriptor actionDescriptor = actionDescriptorLoader.loadActionDescriptor(method);
        actionDescriptor.setControllerDescriptor(controllerDescriptor);

        RouteDescriptor routeDescriptor = loader.loadRouteDescriptor(actionDescriptor);

        assertEquals("galaxies/{galaxyId}/stars/{starId}", routeDescriptor.getMapping());
        assertEquals("galaxies/([\\w]+)/stars/([\\w]+)", routeDescriptor.getMappingPattern());

        assertEquals(2, routeDescriptor.getParameters().size());
        assertTrue(routeDescriptor.getParameters().contains("starId"));
        assertTrue(routeDescriptor.getParameters().contains("galaxyId"));

    }


    @HttpMapping("galaxies")
    private class GalaxyController {

        @GetMapping("{galaxyId}")
        public void Get() {}

        @PostMapping
        public void Add() {}

        @GetMapping
        public void List() {}

        @DeleteMapping
        public void Delete() {}
    }

    @HttpMapping("galaxies/{galaxyId}/stars")
    private class StarController {
        @GetMapping("{starId}")
        public void Get() {}

        @GetMapping
        public void List() {

        }
    }
}