package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.ItemCollection;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class ItemCollectionResolver  implements ITypedValueResolver<ItemCollection> {
    @Override
    public ItemCollection getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getItems();
    }
}
