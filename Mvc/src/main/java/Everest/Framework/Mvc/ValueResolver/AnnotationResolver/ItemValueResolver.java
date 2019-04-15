package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.ItemValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;

import java.lang.reflect.Parameter;

/**
 * Resolves the request itemCollection.
 *
 * if the name of the item is not provided by annotation,
 * the method parameter name is used as item name.
 *
 * @see Everest.Framework.Http.ItemCollection
 * @see ItemValue
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class ItemValueResolver implements IAnnotationValueResolver<ItemValue> {
    @Override
    public Object getVariable(ActionContext actionContext, Parameter parameter, ItemValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        Object obj = actionContext.getHttpContext().getItems().get(name);
        return obj;
    }
}
