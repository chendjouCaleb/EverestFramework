package Everest.Framework.Mvc.Filter;

import java.lang.reflect.Method;

public interface IFilterDescriptorBuilder {

    boolean canBuild(Method method);
    FilterDescriptor build(Method method);
}
