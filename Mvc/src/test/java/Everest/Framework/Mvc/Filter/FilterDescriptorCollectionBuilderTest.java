package Everest.Framework.Mvc.Filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FilterDescriptorCollectionBuilderTest {
    private FilterDescriptorCollectionBuilder builder = new FilterDescriptorCollectionBuilder();

    @Test
    void build() throws NoSuchMethodException {
        Method beforeNamedMethod1 = ActionFilterClass.class.getMethod("OnActionExecuted");
        Method beforeNamedMethod2 = ActionFilterClass.class.getMethod("onActionExecuted");
        Method beforeDecoratedMethod = ActionFilterClass.class.getMethod("afterMethod");

        Method afterNamedMethod1 = ActionFilterClass.class.getMethod("OnActionExecuted");
        Method afterNamedMethod2 = ActionFilterClass.class.getMethod("onActionExecuted");
        Method afterDecoratedMethod = ActionFilterClass.class.getMethod("afterMethod");

        Method nonFilterMethod = ActionFilterClass.class.getMethod("nonFilterMethod");

        FilterDescriptorCollection collection = builder.build(ActionFilterClass.class);

        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(beforeNamedMethod1)));
        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(beforeNamedMethod2)));
        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(beforeDecoratedMethod)));

        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(afterNamedMethod1)));
        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(afterNamedMethod2)));
        Assertions.assertTrue(collection.stream().anyMatch(d -> d.getMethod().equals(afterDecoratedMethod)));

        collection.forEach(d -> assertEquals(d.getType(), ActionFilterClass.class));
        assertEquals(6, collection.size());
    }


    private class ActionFilterClass extends ActionFilter {
        public void OnActionExecuting() {
        }

        public void onActionExecuting() {
        }

        @OnActionExecuting(order = 5)
        public void beforeMethod() {
        }

        public void OnActionExecuted() {
        }

        public void onActionExecuted() {
        }

        @OnActionExecuted(order = 5)
        public void afterMethod() {
        }

        public void nonFilterMethod() {

        }

    }
}