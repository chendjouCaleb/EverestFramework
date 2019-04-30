package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InjectionConstructorGetterTest {
    private InjectionConstructorGetter constructorGetter = new InjectionConstructorGetter();

    @Test
    void getNativeConstructor() throws NoSuchMethodException {
        Constructor constructor = constructorGetter.getConstructor(ClassWithNativeConstructor.class);
        assertEquals(constructor, ClassWithNativeConstructor.class.getConstructor(InjectionConstructorGetterTest.class));
    }

    @Test
    void getSingleConstructor() throws NoSuchMethodException {
        Constructor constructor = constructorGetter.getConstructor(ClassWithSingleConstructor.class);
        assertEquals(constructor, ClassWithSingleConstructor.class
                .getConstructor(InjectionConstructorGetterTest.class, Integer.class));
    }

    @Test
    void getSpecifiedConstructor() throws NoSuchMethodException {
        Constructor constructor = constructorGetter.getConstructor(ClassWithSpecifiedConstructor.class);
        assertEquals(constructor, ClassWithSpecifiedConstructor.class
                .getConstructor(InjectionConstructorGetterTest.class, Integer.class, Double.class));
    }

    @Test
    void getConstructorWithTwoConstructor_WillFail() {
        assertThrows(NoSuchElementException.class, () -> constructorGetter.getConstructor(ClassWithoutInjectorConstructor.class));
    }

    @Test
    void getConstructorWithManySpecifiedConstructor_WillFail() {
        assertThrows(IllegalStateException.class, () -> constructorGetter.getConstructor(ClassWithManyInjectorConstructor.class));
    }

    public class ClassWithNativeConstructor {

    }

    private class ClassWithSingleConstructor {
        public ClassWithSingleConstructor(Integer x) {}
    }

    private class ClassWithSpecifiedConstructor {
        public ClassWithSpecifiedConstructor(Integer x) {}

        @Resolve
        public ClassWithSpecifiedConstructor(Integer x, Double y) {}
    }

    private class ClassWithoutInjectorConstructor{
        public ClassWithoutInjectorConstructor() {}
        public ClassWithoutInjectorConstructor(Integer x) {}
    }

    private class ClassWithManyInjectorConstructor {
        @Resolve
        public ClassWithManyInjectorConstructor() {}

        @Resolve
        public ClassWithManyInjectorConstructor(Integer x) {}
    }

}
