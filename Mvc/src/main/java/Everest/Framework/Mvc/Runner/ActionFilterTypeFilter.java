package Everest.Framework.Mvc.Runner;

import Everest.Framework.InversionOfControl.Packages.ITypeFilter;
import Everest.Framework.Mvc.Filter.IActionFilter;

import javax.annotation.Nonnull;

public class ActionFilterTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class<?> type) {

        return IActionFilter.class.isAssignableFrom(type);
    }

    @Override
    public Class<?> getComponentType(@Nonnull Class<?> type) {
        return type;
    }
}
