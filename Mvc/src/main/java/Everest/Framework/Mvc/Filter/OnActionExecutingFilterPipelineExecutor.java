package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.Reflexions;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.MethodValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * The executor of before action execution filters. A take a collection of
 * {@link OnActionExecuting} filter, an execute them. Before to launch a execution,
 * make sure that all {@link FilterDescriptor} is an before action filter and its have all properties.
 * @see OnActionExecuting
 * @see FilterDescriptor
 * @see FilterDescriptorCollection
 *
 * @author Chendjou
 * @version 1
 * @since 16-05-2016
 */
public class OnActionExecutingFilterPipelineExecutor {
    private IComponentProvider provider;
    private MethodValueResolver valueResolver;
    private FilterDescriptorCollection filterDescriptors;
    private ActionContext actionContext;
    private List<IFilterContract> filters = new ArrayList<>();

    public OnActionExecutingFilterPipelineExecutor(IComponentProvider provider, MethodValueResolver valueResolver,
                                                   FilterDescriptorCollection filterDescriptors, ActionContext actionContext) {
        this.provider = provider;
        this.valueResolver = valueResolver;
        this.filterDescriptors = filterDescriptors;
        this.actionContext = actionContext;
        for(FilterDescriptor descriptor: filterDescriptors) {
            IFilterContract filter = provider.getComponent(descriptor.getType());
            filters.add(filter);
        }
    }

    public FilterPipelineResult execute() {
        FilterPipelineResult result = new FilterPipelineResult();
        for (FilterDescriptor descriptor:filterDescriptors){
            OnActionExecutingContext context = new OnActionExecutingContext(actionContext, filters, actionContext.getControllerInstance());


            IActionFilter filter = (IActionFilter) filters.stream().filter(f -> f.getClass().equals(descriptor.getType())).findFirst().get();

            filter.init(descriptor.getAnnotation());

            Object[] parameters = valueResolver.getVariables(descriptor.getMethod(), context);

            Reflexions.callRemote(filter,descriptor.getMethod(), parameters);

            result.setLastFilter(filter);

            if(context.getResult() != null){
                result.setResult(context.getResult());
                return result;
            }

        }
        return result;
    }
}
