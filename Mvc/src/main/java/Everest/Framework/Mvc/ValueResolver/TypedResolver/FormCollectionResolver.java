package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.FormCollection;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provider the request data form.
 * @see FormCollection
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class FormCollectionResolver implements ITypedValueResolver<FormCollection> {

    public FormCollection getValue(ActionContext context, Parameter parameter) {
        return context.getHttpContext().getRequest().forms();
    }
}
