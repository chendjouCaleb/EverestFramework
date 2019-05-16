package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provides the request HttpContext.
 * @see HttpContext
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class HttpContextResolver implements ITypeValueResolver<HttpContext, ActionContext> {

    public HttpContext getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getHttpContext();
    }
}
