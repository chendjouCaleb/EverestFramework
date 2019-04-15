package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpRequest;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class RequestRevolver implements ITypedValueResolver<HttpRequest> {

    public HttpRequest getValue(HttpContext context, Parameter parameter) {
        return context.getRequest();
    }
}
