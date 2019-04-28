package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Exception.InvalidNameException;
import Everest.Framework.Mvc.Action.ControllerDescriptor;
import Everest.Framework.Mvc.Action.ControllerDescriptorLoader;
import Everest.Framework.Mvc.Mapping.DeleteMapping;
import Everest.Framework.Mvc.Mapping.GetMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.PostMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerDescriptorLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void Try_GetDescriptorOfNotMappedClass() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        assertThrows(UnMappedException.class, () -> loader.LoadControllerDescriptor(NotMappedController.class));
    }

    @Test
    void Try_GetDescriptorOfMalNamedClass() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        assertThrows(InvalidNameException.class, () -> loader.LoadControllerDescriptor(MalNamed.class));
    }

    @Test
    void try_GetDescriptorOfMalformedUrlMappingClass() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        assertThrows(MalformedUrlMappingException.class, () -> loader.LoadControllerDescriptor(MalformedMappingController.class));
    }

    @Test
    void loadControllerDescriptor() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        ControllerDescriptor description = loader.LoadControllerDescriptor(GalaxyController.class);

        assertEquals("Galaxy", description.getName());
        assertEquals("galaxies", description.getMapping());
        assertEquals(GalaxyController.class, description.getClassType());
    }


    @Test
    void loadActionDescriptor() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        ControllerDescriptor description = loader.LoadControllerDescriptor(GalaxyController.class);
        loader.collectActionDescriptor(description);

        assertEquals(4, description.getActionDescriptors().size());
        assertTrue(description.getActionDescriptors()
                .stream().anyMatch(d -> d.getMethod().getName().equals("Get")
                        && d.getMapping().equals("{id}")));

        assertTrue(description.getActionDescriptors()
                .stream().anyMatch(d -> d.getMethod().getName().equals("Add")
                        && d.getMapping().equals("")));

        assertTrue(description.getActionDescriptors()
                .stream().anyMatch(d -> d.getMethod().getName().equals("List")
                        && d.getMapping().equals("")));

        assertTrue(description.getActionDescriptors()
                .stream().anyMatch(d -> d.getMethod().getName().equals("Delete")
                        && d.getMapping().equals("{id}")));
    }

    @Test
    void checkDuplicatedActionName() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        ControllerDescriptor description = loader.LoadControllerDescriptor(GalaxyDuplicateController.class);
        loader.collectActionDescriptor(description);
        loader.collectActionDescriptor(description);
        assertThrows(InvalidNameException.class, () -> loader.checkDuplicatedActionName(description));
    }

    @Test
    void checkDuplicatedActionName_WithoutDuplicatedActionName() {
        ControllerDescriptorLoader loader = new ControllerDescriptorLoader();
        ControllerDescriptor description = loader.LoadControllerDescriptor(GalaxyController.class);
        loader.collectActionDescriptor(description);
        loader.collectActionDescriptor(description);
        loader.checkDuplicatedActionName(description);
    }

    @HttpMapping(value = "galaxies", name = "Galaxy")
    private class GalaxyController {
        @GetMapping("{id}")
        public void Get() {
        }

        @PostMapping
        public void Add() {
        }

        @GetMapping
        public void List() {
        }

        @DeleteMapping("{id}")
        public void Delete() {
        }

        public void noAction() {

        }
    }


    private class NotMappedController {

    }

    @HttpMapping("/mapping")
    private class MalformedMappingController {

    }

    @HttpMapping("mapping")
    private class MalNamed {

    }

    @HttpMapping(value = "galaxies", name = "Galaxy")
    private class GalaxyDuplicateController {
        @GetMapping(value = "{id}", name = "Find")
        public void Get() {
        }

        @GetMapping("{id}")
        public void Find() {
        }
    }
}