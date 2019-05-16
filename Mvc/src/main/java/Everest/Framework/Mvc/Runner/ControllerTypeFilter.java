package Everest.Framework.Mvc.Runner;

import Everest.Framework.InversionOfControl.Packages.ITypeFilter;

import javax.annotation.Nonnull;

public class ControllerTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class<?> type) {
        return type.getName().endsWith("Controller");
    }

    @Override
    public Class<?> getComponentType(@Nonnull Class<?> type) {
        return type;
    }
}
