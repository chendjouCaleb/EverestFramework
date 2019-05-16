package Everest.Framework.Mvc.Runner;

import Everest.Framework.InversionOfControl.Packages.ITypeFilter;
import Everest.Framework.Mvc.Filter.ActionFilter;

import javax.annotation.Nonnull;

public class ActionFilterTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class<?> type) {
        if(type.getName().endsWith("Filter")){
            System.out.println(type.getName() + ": " +type.getSuperclass().equals(ActionFilter.class) );

        }
        return type.getName().endsWith("Filter");
    }

    @Override
    public Class<?> getComponentType(@Nonnull Class<?> type) {
        return type;
    }
}
