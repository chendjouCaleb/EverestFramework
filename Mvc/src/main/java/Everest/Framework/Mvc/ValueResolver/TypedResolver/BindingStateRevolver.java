package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;


import java.lang.reflect.Parameter;

public class BindingStateRevolver implements ITypedValueResolver<BindingState> {

    public BindingState getValue(HttpContext httpContext, Parameter parameter) {

        return httpContext.getBindingState();
    }


}
