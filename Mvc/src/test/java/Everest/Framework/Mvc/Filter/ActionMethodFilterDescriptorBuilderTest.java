package Everest.Framework.Mvc.Filter;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionMethodFilterDescriptorBuilderTest {
    private ActionMethodFilterDescriptorBuilder builder =
            new ActionMethodFilterDescriptorBuilder(new AnnotationFilterGetter(), new FilterDescriptorCollectionBuilder());

    @Test
    void getMethodFiltersDescriptors() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod1");
        FilterDescriptorCollection collection = builder.getFiltersDescriptors(method);

        assertEquals(4, collection.size());
        //The collected filter must be ordered by declaration position

        assertEquals(Filter1.class, collection.get(0).getAnnotation().annotationType());
        assertEquals(Filter1.class, collection.get(1).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(2).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(3).getAnnotation().annotationType());

        assertEquals(ActionFilter1Class.class, collection.get(0).getType());
        assertEquals(ActionFilter1Class.class, collection.get(1).getType());
        assertEquals(ActionFilter2Class.class, collection.get(2).getType());
        assertEquals(ActionFilter2Class.class, collection.get(3).getType());

        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuting")).isIn(collection.get(0).getMethod(), collection.get(1).getMethod());
        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuted")).isIn(collection.get(0).getMethod(), collection.get(1).getMethod());

        assertThat(ActionFilter2Class.class.getMethod("BeforeAction")).isIn(collection.get(2).getMethod(), collection.get(3).getMethod());
        assertThat(ActionFilter2Class.class.getMethod("AfterAction")).isIn(collection.get(2).getMethod(), collection.get(3).getMethod());

    }

    @Test
    void getAllFilter() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod1");
        FilterDescriptorCollection collection = builder.getFiltersDescriptors(Controller.class, method);

        assertEquals(8, collection.size());
        //The collected filter must be ordered by declaration position

        assertEquals(Filter1.class, collection.get(0).getAnnotation().annotationType());
        assertEquals(Filter1.class, collection.get(1).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(2).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(3).getAnnotation().annotationType());

        assertEquals(Filter1.class, collection.get(4).getAnnotation().annotationType());
        assertEquals(Filter1.class, collection.get(5).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(6).getAnnotation().annotationType());
        assertEquals(Filter2.class, collection.get(7).getAnnotation().annotationType());

        assertEquals(ActionFilter1Class.class, collection.get(0).getType());
        assertEquals(ActionFilter1Class.class, collection.get(1).getType());
        assertEquals(ActionFilter2Class.class, collection.get(2).getType());
        assertEquals(ActionFilter2Class.class, collection.get(3).getType());
        assertEquals(ActionFilter1Class.class, collection.get(4).getType());
        assertEquals(ActionFilter1Class.class, collection.get(5).getType());
        assertEquals(ActionFilter2Class.class, collection.get(6).getType());
        assertEquals(ActionFilter2Class.class, collection.get(7).getType());

        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuting")).isIn(collection.get(0).getMethod(), collection.get(1).getMethod());
        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuted")).isIn(collection.get(0).getMethod(), collection.get(1).getMethod());

        assertThat(ActionFilter2Class.class.getMethod("BeforeAction")).isIn(collection.get(2).getMethod(), collection.get(3).getMethod());
        assertThat(ActionFilter2Class.class.getMethod("AfterAction")).isIn(collection.get(2).getMethod(), collection.get(3).getMethod());

        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuting")).isIn(collection.get(4).getMethod(), collection.get(5).getMethod());
        assertThat(ActionFilter1Class.class.getMethod("OnActionExecuted")).isIn(collection.get(4).getMethod(), collection.get(5).getMethod());

        assertThat(ActionFilter2Class.class.getMethod("BeforeAction")).isIn(collection.get(6).getMethod(), collection.get(7).getMethod());
        assertThat(ActionFilter2Class.class.getMethod("AfterAction")).isIn(collection.get(6).getMethod(), collection.get(7).getMethod());

    }

    @Test
    void getFiltersDescriptors1() {
    }

    @FilterBy(filter = ActionFilter1Class.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface Filter1 {

    }

    private class ActionFilter1Class implements IActionFilter<Filter1> {
        public void init(Filter1 annotation) { }

        public void OnActionExecuting() {}
        public void OnActionExecuted() {}

    }

    @FilterBy(filter = ActionFilter2Class.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface Filter2 {

    }

    private class ActionFilter2Class implements IActionFilter<Filter2> {
        public void init(Filter2 annotation) { }

        @OnActionExecuting
        public void BeforeAction() {}

        @OnActionExecuted
        public void AfterAction() {}
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