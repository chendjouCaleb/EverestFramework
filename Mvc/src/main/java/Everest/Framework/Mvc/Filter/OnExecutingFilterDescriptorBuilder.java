package Everest.Framework.Mvc.Filter;

import java.lang.reflect.Method;

/**
 * To build a {@link FilterDescriptor} of an
 * OnExecutingFilter method
 */
public class OnExecutingFilterDescriptorBuilder implements IFilterDescriptorBuilder{
    @Override
    public boolean canBuild(Method method) {
        return method.isAnnotationPresent(OnActionExecuting.class)
                || method.getName().equals("OnActionExecuting")
                || method.getName().equals("onActionExecuting");
    }

    @Override
    public FilterDescriptor build(Method method) {
        FilterDescriptor descriptor = new FilterDescriptor();
        descriptor.setMethod(method);
        descriptor.setFilterType(FilterType.OnActionExecuting);
        OnActionExecuting onActionExecuting = method.getAnnotation(OnActionExecuting.class);
        if(onActionExecuting != null){
            descriptor.setOrder(onActionExecuting.order());
        }
        return descriptor;
    }

}
