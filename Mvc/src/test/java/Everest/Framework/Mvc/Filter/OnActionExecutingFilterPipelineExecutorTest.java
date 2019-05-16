package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Http.DefaultHttpContext;
import Everest.Framework.InversionOfControl.DI.ComponentProviderBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Result.EntityResult;
import Everest.Framework.Mvc.ValueResolver.MethodValueResolver;
import Everest.Framework.Mvc.ValueResolver.TypedResolver.ActionContextRevolver;
import Everest.Framework.Mvc.ValueResolver.ValueResolverProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class OnActionExecutingFilterPipelineExecutorTest {
    OnActionExecutingFilterPipelineExecutor executor;
    private IComponentProvider componentProvider;
    private ActionMethodFilterDescriptorBuilder filterDescriptorBuilder;
    private MethodValueResolver methodValueResolver;

    @BeforeEach
    void setUp() {
        ComponentRegister register = new ComponentRegister();
        register.addSingleton(new Controller());
        register.addSingleton(new ActionFilter1Class());
        register.addSingleton(new ActionFilter2Class());
        register.addSingleton(new ShortCircuitActionFilterClass());
        componentProvider = new ComponentProviderBuilder().buildProvider(register);

        filterDescriptorBuilder =
                new ActionMethodFilterDescriptorBuilder(new AnnotationFilterGetter(), new FilterDescriptorCollectionBuilder());

        ValueResolverProvider resolverProvider = new ValueResolverProvider();
        resolverProvider.addResolver(new OnActionExecutingContextResolver());
        resolverProvider.addResolver(new ActionContextRevolver());
        methodValueResolver = new MethodValueResolver(resolverProvider);
    }

    @Test
    void execute() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod1");

        FilterDescriptorCollection filterDescriptors = filterDescriptorBuilder
                .getFiltersDescriptors(Controller.class, method);
        ActionContext actionContext = new ActionContext();
        actionContext.setControllerInstance(new Controller());
        actionContext.setHttpContext(new DefaultHttpContext());
        executor = new OnActionExecutingFilterPipelineExecutor(componentProvider, methodValueResolver, filterDescriptors, actionContext);

        FilterPipelineResult result = executor.execute();

        assertEquals(ActionFilter2Class.class, result.getLastFilter().getClass());
        assertNull(result.getResult());
        assertTrue(result.isFinished());
    }


    @Test
    void execute_WithShort_CircuitFilter() throws NoSuchMethodException {
        Method method = Controller.class.getMethod("actionMethod2");

        FilterDescriptorCollection filterDescriptors = filterDescriptorBuilder
                .getFiltersDescriptors(Controller.class, method);
        ActionContext actionContext = new ActionContext();
        actionContext.setControllerInstance(new Controller());
        actionContext.setHttpContext(new DefaultHttpContext());
        executor = new OnActionExecutingFilterPipelineExecutor(componentProvider, methodValueResolver, filterDescriptors, actionContext);

        FilterPipelineResult result = executor.execute();

        assertEquals(ShortCircuitActionFilterClass.class, result.getLastFilter().getClass());
        assertEquals("The filter pipeline stop message", ((EntityResult)result.getResult()).getBody());
        assertFalse(result.isFinished());
    }

    @FilterBy(filter = ActionFilter1Class.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface Filter1 {

    }

    private class ActionFilter1Class implements IActionFilter<Filter1> {
        public void init(Filter1 annotation) {
            System.out.println("Init");
        }
        public void OnActionExecuting() {
            System.out.println("ActionFilter1Class: OnActionExecuting");
        }
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
        public void BeforeAction(OnActionExecutingContext context) {

        }

        @OnActionExecuted
        public void AfterAction() {}
    }

    @FilterBy(filter = ShortCircuitActionFilterClass.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    private  @interface ShortCircuitFilter {
        String message() default  "The filter pipeline stop message";
    }

    private class ShortCircuitActionFilterClass implements IActionFilter<ShortCircuitFilter> {
        private String message;
        @Override
        public void init(ShortCircuitFilter annotation) {
            message = annotation.message();
        }

        @OnActionExecuting
        public void shortCircuit(OnActionExecutingContext context) {
            System.out.println("Stop");
            context.setResult(new EntityResult<String>(message));
        }
    }

    @Filter1
    @Filter2
    private class Controller{
        @Filter1
        @Filter2
        public void actionMethod1() {

        }

        @ShortCircuitFilter
        @Filter2
        public void actionMethod2() {}
    }
}