package Everest.Framework.Mvc.ResponseFormatter;

import Everest.Framework.InversionOfControl.Packages.ITypeFilter;

public class ResponseFormatterTypeFilter implements ITypeFilter {
    @Override
    public boolean isEligible(Class type) {
        return type.getInterfaces().length == 1 && type.getInterfaces()[0].equals(IResponseFormatter.class);
    }
}
