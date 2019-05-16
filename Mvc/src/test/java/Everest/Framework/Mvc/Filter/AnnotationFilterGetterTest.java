package Everest.Framework.Mvc.Filter;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnotationFilterGetterTest {
    private AnnotationFilterGetter getter = new AnnotationFilterGetter();

    @Test
    void getMethodAnnotation() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod1");
        List<Annotation> annotations = getter.getFilterAnnotations(method);
        assertEquals(2, annotations.size());
        assertEquals(Filter1.class, annotations.get(0).annotationType());
        assertEquals(Filter2.class, annotations.get(1).annotationType());
    }

    @Test
    void getMethodAnnotation_WithoutAnnotation() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod2");
        List<Annotation> annotations = getter.getFilterAnnotations(method);
        assertEquals(0, annotations.size());
    }

    @Test
    void getClassTypeAnnotation(){
        List<Annotation> annotations = getter.getFilterAnnotations(Controller.class);
        assertEquals(2, annotations.size());
        assertEquals(Filter1.class, annotations.get(0).annotationType());
        assertEquals(Filter2.class, annotations.get(1).annotationType());
    }

    @FilterBy(filter = ActionFilter1Class.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface Filter1 {

    }

    private class ActionFilter1Class implements IActionFilter<Filter1> {
        public void init(Filter1 annotation) { }
    }

    @FilterBy(filter = ActionFilterClass2.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface Filter2 {

    }

    private class ActionFilterClass2 implements IActionFilter<Filter2> {
        public void init(Filter2 annotation) { }
    }

    @Filter1
    @Filter2
    private class Controller{
        @Filter1
        @Filter2
        public void actionMethod1() {

        }

        public void actionMethod2() {}
    }
}