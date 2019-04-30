package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.PostInit;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostInitMethodGetterTest {
    private PostInitMethodGetter getter = new PostInitMethodGetter();

    @Test
    void getPostInitMethods() throws NoSuchMethodException {
        List<Method> methods = getter.getPostInitMethods(Planet.class);
        assertEquals(4, methods.size());

        assertTrue(methods.contains(Planet.class.getDeclaredMethod("setDayDuration")));
        assertTrue(methods.contains(Planet.class.getDeclaredMethod("postInit")));

        assertTrue(methods.contains(CelestialBody.class.getDeclaredMethod("initMeasures")));
        assertTrue(methods.contains(CelestialBody.class.getDeclaredMethod("setInitMass")));
    }

    @Test
    void getInitMethod_WithParameter_WillFail(){
        assertThrows(IllegalStateException.class, () -> getter.getPostInitMethods(InitMethodWithParameter.class));
    }

    //To verify that the getter get the methods of base class.
    private class CelestialBody{
        @PostInit
        private Double initMeasures() {
            return 0.0;
        }

        @PostInit
        public void setInitMass() {
        }
    }

    private class Planet extends CelestialBody {
        @PostInit
        public void postInit() {}

        @PostInit
        private void setDayDuration() {}

        public void setYearDuration(Double duration) {}
    }

    public class InitMethodWithParameter {
        @PostInit
        public void init(Integer value) {

        }
    }

}