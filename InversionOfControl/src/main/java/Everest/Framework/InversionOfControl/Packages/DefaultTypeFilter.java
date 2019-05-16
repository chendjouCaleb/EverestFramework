package Everest.Framework.InversionOfControl.Packages;


import Everest.Framework.Core.Inject.Scope;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Core.Inject.Transient;

public class DefaultTypeFilter implements ITypeFilter {

    @Override
    public boolean isEligible(Class<?> type) {
        return type.isAnnotationPresent(Singleton.class)
                || type.isAnnotationPresent(Transient.class)
                || type.isAnnotationPresent(Scope.class);
    }
}
