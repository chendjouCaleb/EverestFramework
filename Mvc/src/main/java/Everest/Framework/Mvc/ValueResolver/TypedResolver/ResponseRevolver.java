package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provides the response.
 * @see HttpResponse
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ResponseRevolver implements ITypedValueResolver<HttpResponse> {

    @Override
    public HttpResponse getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getHttpContext().getResponse();
    }
}
