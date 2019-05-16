package Everest.Framework.Mvc.Filter;

import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

public class OnActionExecutingContextResolver implements ITypeValueResolver<OnActionExecutingContext, OnActionExecutingContext> {
    @Override
    public OnActionExecutingContext getValue(OnActionExecutingContext actionContext, Parameter parameter) {
        return actionContext;
    }
}
