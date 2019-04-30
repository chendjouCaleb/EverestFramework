package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InjectionFieldGetterTest {

    @Test
    void getInjectionField() throws NoSuchFieldException {

        InjectionFieldGetter getter = new InjectionFieldGetter();
        List<Field> fields = getter.getInjectionField(Planet.class);
        assertTrue(fields.contains(Planet.class.getDeclaredField("radius")));
        assertTrue(fields.contains(Planet.class.getDeclaredField("dayDuration")));

        assertTrue(fields.contains(CelestialBody.class.getDeclaredField("name")));
        assertTrue(fields.contains(CelestialBody.class.getDeclaredField("age")));
        assertTrue(fields.contains(CelestialBody.class.getDeclaredField("mass")));


        assertEquals(5, fields.size());
    }

    //To verify that the getter get the field of base class.
    private class CelestialBody{

        @Resolve
        protected String name;

        @Resolve
        public Double age;

        @Resolve
        private Integer mass;
    }

    private class Planet extends CelestialBody {
        @Resolve
        public Double radius;

        @Resolve
        private Double dayDuration;

        public Double yearDuration;
    }
}