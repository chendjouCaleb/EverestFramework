package Everest.Framework.Mvc.ValueResolver;


import Everest.Framework.InversionOfControl.Packages.ITypeFilter;

public class TypedValueResolverTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class type) {
        return type.getInterfaces().length == 1 && type.getInterfaces()[0].equals(ITypeValueResolver.class);
    }
}
