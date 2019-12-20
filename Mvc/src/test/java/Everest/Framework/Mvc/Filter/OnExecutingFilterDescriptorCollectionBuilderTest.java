package Everest.Framework.Mvc.Filter;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class OnExecutingFilterDescriptorCollectionBuilderTest {
    private OnExecutingFilterDescriptorBuilder builder = new OnExecutingFilterDescriptorBuilder();
    @Test
    void canBuild() throws NoSuchMethodException {
        Method namedMethod1 = FilterClass.class.getMethod("OnActionExecuting");
        Method namedMethod2 = FilterClass.class.getMethod("onActionExecuting");
        Method decoratedMethod = FilterClass.class.getMethod("filterMethod");
        Method nonFilterMethod = FilterClass.class.getMethod("nonFilterMethod");

        assertTrue(builder.canBuild(namedMethod1));
        assertTrue(builder.canBuild(namedMethod2));
        assertTrue(builder.canBuild(decoratedMethod));
        assertFalse(builder.canBuild(nonFilterMethod));
    }

    @Test
    void build_namedMethod() throws NoSuchMethodException {
        Method method = FilterClass.class.getMethod("OnActionExecuting");
        FilterDescriptor descriptor = builder.build(method);

        assertEquals(method, descriptor.getMethod());
        assertEquals(FilterType.OnActionExecuting, descriptor.getFilterType());
        assertEquals(0, descriptor.getOrder());
    }

    @Test
    void build_NamedMethod() throws NoSuchMethodException {
        Method method = FilterClass.class.getMethod("onActionExecuting");
        FilterDescriptor descriptor = builder.build(method);

        assertEquals(method, descriptor.getMethod());
        assertEquals(FilterType.OnActionExecuting, descriptor.getFilterType());
        assertEquals(0, descriptor.getOrder());
    }

    @Test
    void build_decoratedMethod() throws NoSuchMethodException {
        Method method = FilterClass.class.getMethod("filterMethod");
        FilterDescriptor descriptor = builder.build(method);

        assertEquals(method, descriptor.getMethod());
        assertEquals(FilterType.OnActionExecuting, descriptor.getFilterType());
        assertEquals(5, descriptor.getOrder());
    }

    private class FilterClass {
        public void OnActionExecuting() {

        }

        public void onActionExecuting() {

        }

        @OnActionExecuting(order = 5)
        public void filterMethod() {

        }

        public void nonFilterMethod() {

        }
    }
}