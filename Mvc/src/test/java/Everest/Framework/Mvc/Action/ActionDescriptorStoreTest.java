package Everest.Framework.Mvc.Action;

import Everest.Framework.Core.Exception.InvalidNameException;
import Everest.Framework.Mvc.Mapping.DeleteMapping;
import Everest.Framework.Mvc.Mapping.GetMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.PostMapping;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActionDescriptorStoreTest {

    @Test
    void collect() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(GalaxyController.class);
        classes.add(PlanetController.class);

        ActionDescriptorStore collector = new ActionDescriptorStore();
        collector.setControllerClasses(classes);
        collector.collect();

        assertEquals(2, collector.getControllerDescriptors().size());
        assertEquals(8, collector.getActionDescriptors().size());

        ControllerDescriptor galaxyController = collector.getControllerDescriptors()
                .stream().filter(c -> c.getClassType().equals(GalaxyController.class)).findFirst().get();

        assertEquals("galaxies", galaxyController.getMapping());
        assertEquals("Galaxy", galaxyController.getName());
        assertEquals(4, galaxyController.getActionDescriptors().size());

        assertTrue(collector.getControllerDescriptors().stream().anyMatch(c -> c.getClassType().equals(PlanetController.class)));

        assertTrue(collector.getActionDescriptors().stream().anyMatch(a -> a.getMethod().getName().equals("Add")));
    }



    @Test
    void checkDuplicateControllerName() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(GalaxyController.class);
        classes.add(GalaxyDuplicatedNameController.class);

        ActionDescriptorStore collector = new ActionDescriptorStore();
        collector.setControllerClasses(classes);
        collector.collect();

        assertThrows(InvalidNameException.class, collector::checkDuplicateControllerName);
    }

    @Test
    void checkDuplicateControllerName_WithNoDuplication() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(GalaxyController.class);
        classes.add(PlanetController.class);

        ActionDescriptorStore collector = new ActionDescriptorStore();
        collector.setControllerClasses(classes);
        collector.collect();
        collector.checkDuplicateControllerName();
    }

    @Test
    public void checkDuplicatedActionName() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(GalaxyController.class);
        classes.add(GalaxyDuplicatedNameController.class);

        ActionDescriptorStore collector = new ActionDescriptorStore();
        collector.setControllerClasses(classes);
        collector.collect();


        assertThrows(InvalidNameException.class, collector::checkDuplicateUrlMapping);
    }

    @HttpMapping("galaxies")
    private class GalaxyController {

        @GetMapping("{id}")
        public void Get() {}

        @PostMapping
        public void Add() {}

        @GetMapping
        public void List() {}

        @DeleteMapping
        public void Delete() {}

        public void noAction() {

        }
    }

    @HttpMapping("planets")
    private class PlanetController {

        @GetMapping("{id}")
        public void Get() {}

        @PostMapping
        public void Add() {}

        @GetMapping
        public void List() {}

        @DeleteMapping
        public void Delete() {}
    }

    @HttpMapping(name = "Galaxy")
    private class GalaxyDuplicatedNameController{
        @GetMapping("{id}")
        public void Get() {}
    }
}