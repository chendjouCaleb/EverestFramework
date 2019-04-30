package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InjectionMethodGetterTest {
    @Test
    void getInjectionMethod() throws NoSuchMethodException {

        InjectionMethodGetter getter = new InjectionMethodGetter();
        List<Method> methods = getter.getInjectionMethods(Planet.class);
        assertTrue(methods.contains(Planet.class.getDeclaredMethod("setRadius", Double.class)));
        assertTrue(methods.contains(Planet.class.getDeclaredMethod("setDayDuration", Integer.class)));

        assertTrue(methods.contains(CelestialBody.class.getDeclaredMethod("name", String.class)));
        assertTrue(methods.contains(CelestialBody.class.getDeclaredMethod("age")));
        assertTrue(methods.contains(CelestialBody.class.getDeclaredMethod("setMass", Double.class)));


        assertEquals(5, methods.size());
    }

    @Test
    void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CelestialBody.class.getDeclaredMethod("name", String.class);
        Planet planet = new Planet();
        method.invoke(planet, "abc");

        Method privateMethod = CelestialBody.class.getDeclaredMethod("setMass", Double.class);
        privateMethod.setAccessible(true);

        privateMethod.invoke(planet, 10.0);
        //System.out.println(planet.getMass());
    }

    //To verify that the getter get the field of base class.
    private class CelestialBody{
        private Double mass;

        @Resolve
        protected String name(String name) { return null;}

        @Resolve
        public void age() {}

        @Resolve
        private CelestialBody setMass(Double mass) {
            this.mass = mass;
            return this;
        }


        public Double getMass() {
            return mass;
        }
    }

    private class Planet extends CelestialBody {
        @Resolve
        public void setRadius(Double a) {}

        @Resolve
        private void setDayDuration(Integer duration) {}

        public void setYearDuration(Double duration) {}
    }
}