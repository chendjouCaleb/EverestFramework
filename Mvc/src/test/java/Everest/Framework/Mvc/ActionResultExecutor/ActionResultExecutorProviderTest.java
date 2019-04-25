package Everest.Framework.Mvc.ActionResultExecutor;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Http.HttpContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionResultExecutorProviderTest {

    private ActionResultExecutorProvider executorProvider = new ActionResultExecutorProvider();

    @BeforeEach
    void setUp() {
    }

    @Test
    void addExecutor() {
        Fake1ResultExecutor executor1 = new Fake1ResultExecutor();
        Fake2ResultExecutor executor2 = new Fake2ResultExecutor();
        executorProvider.addExecutor(executor1);
        executorProvider.addExecutor(executor2);

        assertEquals(2, executorProvider.getExecutors().size());

    }

    @Test
    void addExecutor_withoutGenerics(){
        Fake3ResultExecutor executor = new Fake3ResultExecutor();
        assertThrows(InvalidOperationException.class, () -> executorProvider.addExecutor(executor));
    }

    @Test
    void getExecutor() {
        Fake1ResultExecutor executor1 = new Fake1ResultExecutor();
        Fake2ResultExecutor executor2 = new Fake2ResultExecutor();
        executorProvider.addExecutor(executor1);
        executorProvider.addExecutor(executor2);

        assertEquals(executor1, executorProvider.getExecutor(FakeResult1.class));
        assertEquals(executor2, executorProvider.getExecutor(FakeResult2.class));
    }


    class FakeResult1{ }

    class FakeResult2{ }

    private class Fake1ResultExecutor implements IResultExecutor<FakeResult1>{
        public void execute(HttpContext httpContext, FakeResult1 result) {  }
    }

    private class Fake2ResultExecutor implements IResultExecutor<FakeResult2>{
        public void execute(HttpContext httpContext, FakeResult2 result) {  }
    }

    class Fake3ResultExecutor implements IResultExecutor{
        public void execute(HttpContext httpContext, Object result) {  }
    }
}

