package Everest.Framework.Mvc.ValueResolver.TypedResolver;


import Everest.Framework.Http.HttpRequest;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provides the request.
 * @see HttpRequest
 */
public class RequestRevolver implements ITypedValueResolver<HttpRequest> {

    public HttpRequest getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getHttpContext().getRequest();
    }
}
