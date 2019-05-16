package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.ItemCollection;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

/**
 * Providers all items of the request HttpContext.
 * @see ItemCollection
 * @see HttpContext
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ItemCollectionResolver  implements ITypeValueResolver<ItemCollection, ActionContext> {
    @Override
    public ItemCollection getValue(ActionContext actionContext, Parameter parameter) {
        return actionContext.getHttpContext().getItems();
    }
}
