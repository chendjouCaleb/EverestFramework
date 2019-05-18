package Everest.Framework.InversionOfControl.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {

    @Test
    void isConcrete() {
        assertFalse(ClassUtils.isConcrete(AbstractClass.class));
        assertFalse(ClassUtils.isConcrete(InterfaceClass.class));
        assertTrue(ClassUtils.isConcrete(EnumClass.class));
        assertFalse(ClassUtils.isConcrete(AnnotationClass.class));
        assertTrue(ClassUtils.isConcrete(ConcreteClass1.class));
        assertTrue(ClassUtils.isConcrete(ConcreteClass2.class));
        assertTrue(ClassUtils.isConcrete(ConcreteClass3.class));
    }

    private abstract class AbstractClass{ }
    private interface InterfaceClass {}
    private enum EnumClass {}
    private @interface AnnotationClass{}

    private class ConcreteClass1 {}
    private class ConcreteClass2 extends AbstractClass {}
    private class ConcreteClass3 implements InterfaceClass {}
}