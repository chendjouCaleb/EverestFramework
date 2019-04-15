package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class HttpContextResolver implements ITypedValueResolver<HttpContext> {

    public HttpContext getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext;
    }
}
