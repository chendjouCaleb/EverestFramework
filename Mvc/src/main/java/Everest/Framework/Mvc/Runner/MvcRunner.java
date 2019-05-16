package Everest.Framework.Mvc.Runner;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationContext;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Framework.Mvc.ActionInvocation.ActionMethodInvoker;
import Everest.Framework.Mvc.ActionResultExecutor.ActionResultExecutor;
import Everest.Framework.Mvc.Filter.FilterPipelineResult;
import Everest.Framework.Mvc.Filter.OnActionExecutingFilterPipelineExecutor;
import Everest.Framework.Mvc.Routing.RouteDescriptor;
import Everest.Framework.Mvc.Routing.RouteDispatcher;
import Everest.Framework.Mvc.Routing.RouteValues;
import Everest.Framework.Mvc.Routing.RouteValuesExtractor;
import Everest.Framework.Mvc.ValueResolver.MethodValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Singleton
public class MvcRunner {
    private Logger logger = LoggerFactory.getLogger(MvcRunner.class);
    private RouteDispatcher routeDispatcher;
    private RouteValuesExtractor routeValuesExtractor;
    private ActionMethodInvoker actionMethodInvoker;
    private IComponentProvider componentProvider;
    private MethodValueResolver methodValueResolver;
    private ActionResultExecutor actionResultExecutor;


    public MvcRunner(RouteDispatcher routeDispatcher, RouteValuesExtractor routeValuesExtractor, ActionMethodInvoker actionMethodInvoker, IComponentProvider componentProvider,
                     MethodValueResolver methodValueResolver, ActionResultExecutor actionResultExecutor) {
        this.routeDispatcher = routeDispatcher;
        this.routeValuesExtractor = routeValuesExtractor;
        this.actionMethodInvoker = actionMethodInvoker;
        this.componentProvider = componentProvider;
        this.methodValueResolver = methodValueResolver;
        this.actionResultExecutor = actionResultExecutor;
    }

    public void run(HttpContext httpContext){
        logger.debug("Request: [url = {}, method={}]", httpContext.getRequest().path(), httpContext.getRequest().method());
        RouteDescriptor routeDescriptor = routeDispatcher.selectRoute(httpContext.getRequest().path(), httpContext.getRequest().method());

        RouteValues routeValues = routeValuesExtractor.extract(httpContext.getRequest().path(), routeDescriptor);

        ActionContext actionContext = new ActionContext();
        actionContext.setHttpContext(httpContext);
        actionContext.setActionDescriptor(routeDescriptor.getActionDescriptor());
        actionContext.setRouteDescriptor(routeDescriptor);
        actionContext.setControllerDescriptor(routeDescriptor.getActionDescriptor().getControllerDescriptor());
        actionContext.setRouteValues(routeValues);
        actionContext.setControllerInstance(
                componentProvider.getComponent(routeDescriptor.getActionDescriptor().getControllerDescriptor().getClassType())
        );

        //filters execution.
        OnActionExecutingFilterPipelineExecutor beforeFilterExecutor = new OnActionExecutingFilterPipelineExecutor(
                componentProvider, methodValueResolver, routeDescriptor.getActionFilters().getBeforeActionFilters(), actionContext
        );
        FilterPipelineResult filterPipelineResult = beforeFilterExecutor.execute();
        if(!filterPipelineResult.isFinished()){
            actionResultExecutor.execute(httpContext, filterPipelineResult.getResult());
        }else{
            Object[] parameters = methodValueResolver.getVariables(actionContext);

            ActionInvocationContext invocationContext = new ActionInvocationContext();
            invocationContext.setController(actionContext.getControllerInstance());
            invocationContext.setMethod(actionContext.getActionDescriptor().getMethod());
            invocationContext.setParams(parameters);
            ActionInvocationResult result = actionMethodInvoker.invoke(invocationContext);

            actionResultExecutor.execute(httpContext, result);
        }
    }
}
