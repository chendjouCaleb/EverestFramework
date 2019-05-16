package Everest.Framework.Mvc.Filter;

import java.lang.reflect.Method;

/**
 * To build a {@link FilterDescriptor} of an
 * OnExecutedFilter method
 */
public class OnExecutedFilterDescriptorBuilder implements IFilterDescriptorBuilder{
    @Override
    public boolean canBuild(Method method) {
        return method.isAnnotationPresent(OnActionExecuted.class)
                || method.getName().equals("OnActionExecuted")
                || method.getName().equals("onActionExecuted");
    }

    @Override
    public FilterDescriptor build(Method method) {
        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setMethod(method);
        descriptor.setFilterType(FilterType.OnActionExecuted);
        OnActionExecuted onActionExecuted = method.getAnnotation(OnActionExecuted.class);
        if(onActionExecuted != null){
            descriptor.setOrder(onActionExecuted.order());
        }
        return descriptor;
    }

}
