package Everest.Framework.Mvc.Middleware;


import Everest.Framework.InversionOfControl.Packages.ITypeFilter;

public class MiddlewareTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class type) {
        return type.getInterfaces().length == 1 && type.getInterfaces()[0].equals(IMiddleware.class);
    }
}
