package Everest.Framework.Mvc.ActionResultExecutor;

import Everest.Framework.InversionOfControl.Packages.ITypeFilter;

public class ActionResultExecutorTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class type) {
        return type.getInterfaces().length == 1 && type.getInterfaces()[0].equals(IResultExecutor.class);
    }
}
