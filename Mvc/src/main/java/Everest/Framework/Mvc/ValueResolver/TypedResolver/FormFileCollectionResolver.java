package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.FormFileCollection;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provider the request form files.
 * @see FormFileCollection
 *
 * @author Chendjou
 * @version 1
 * @since 01-07-2019
 */
public class FormFileCollectionResolver implements ITypeValueResolver<FormFileCollection, ActionContext> {

    public FormFileCollection getValue(ActionContext context, Parameter parameter) {
        return context.getHttpContext().getRequest().getFiles();
    }
}
