package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.ItemValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;

import java.lang.reflect.Parameter;

public class ItemValueResolver implements IAnnotationValueResolver<ItemValue> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, ItemValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        Object obj = httpContext.getItems().get(name);
        return obj;
    }
}
