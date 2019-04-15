package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class ResponseRevolver implements ITypedValueResolver<HttpResponse> {

    @Override
    public HttpResponse getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getResponse();
    }
}
