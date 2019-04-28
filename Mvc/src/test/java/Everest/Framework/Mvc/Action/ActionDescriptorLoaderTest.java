package Everest.Framework.Mvc.Action;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Mapping.GetMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingFor.MappingGetter;
import Everest.Framework.Mvc.Routing.MalformedUrlMappingException;
import Everest.Framework.Mvc.Routing.UnMappedException;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActionDescriptorLoaderTest {
    private Class controllerType = NebulaController.class;
    @Test
    void loadActionDescriptor() {
        Method addNebula = MethodUtils.getMatchingMethod(controllerType, "GetNebula");
        ActionDescriptorLoader loader = new ActionDescriptorLoader(new MappingGetter());

        ActionDescriptor descriptor = loader.loadActionDescriptor(addNebula);
        assertEquals("addNebula", descriptor.getName());
        assertEquals(HttpMethod.POST, descriptor.getVerbs());
        assertEquals("nebula", descriptor.getMapping());
        assertEquals(addNebula, descriptor.getMethod());
    }

    @Test
    void loadActionDescriptorOnAnnotatedAnnotatedMethod() {
        Method getNebula = MethodUtils.getMatchingMethod(controllerType, "GetNebula");
        ActionDescriptorLoader loader = new ActionDescriptorLoader(new MappingGetter());

        ActionDescriptor descriptor = loader.loadActionDescriptor(getNebula);
        assertEquals("getNebula", descriptor.getName());
        assertEquals(HttpMethod.GET, descriptor.getVerbs());
        assertEquals("nebula", descriptor.getMapping());
        assertEquals(getNebula, descriptor.getMethod());
    }


    @Test
    void loadActionDescriptor_WithoutMappingName() {
        Method listNebula = MethodUtils.getMatchingMethod(controllerType, "ListNebula");
        ActionDescriptorLoader loader = new ActionDescriptorLoader(new MappingGetter());

        ActionDescriptor descriptor = loader.loadActionDescriptor(listNebula);
        assertEquals("ListNebula", descriptor.getName());
        assertEquals(HttpMethod.GET, descriptor.getVerbs());
        assertEquals("nebula", descriptor.getMapping());
        assertEquals(listNebula, descriptor.getMethod());
    }

    @Test
    void TryGetDescriptorOfNotMappingMethod() {
        Method NotMappingMethod = MethodUtils.getMatchingMethod(controllerType, "NotMappingMethod");
        ActionDescriptorLoader loader = new ActionDescriptorLoader(new MappingGetter());

        assertThrows(UnMappedException.class ,() -> loader.loadActionDescriptor(NotMappingMethod));
    }


    @Test
    void TryGetDescriptorOfMalformedUrlMappingMethod() {
        Method MalformedMapping = MethodUtils.getMatchingMethod(controllerType, "MalformedMapping");
        ActionDescriptorLoader loader = new ActionDescriptorLoader(new MappingGetter());

        assertThrows(MalformedUrlMappingException.class ,() -> loader.loadActionDescriptor(MalformedMapping));
    }

    private class NebulaController{

        @HttpMapping(name = "addNebula", verbs = HttpMethod.POST, value = "nebula")
        public void AddNebula() {

        }

        @GetMapping(name = "getNebula", value = "nebula")
        public void GetNebula(){

        }

        @GetMapping("nebula")
        public void ListNebula() {

        }

        public void NotMappingMethod(){ }

        @HttpMapping("/route")
        public void MalformedMapping(){

        }
    }
}