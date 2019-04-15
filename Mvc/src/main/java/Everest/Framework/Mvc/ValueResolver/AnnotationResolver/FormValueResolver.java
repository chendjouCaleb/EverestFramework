package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.Annotations.FormValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;


import java.lang.reflect.Parameter;

public class FormValueResolver implements IAnnotationValueResolver<FormValue> {
   private IModelBinder modelBinder;

    public FormValueResolver(IModelBinder modelBinder) {
        this.modelBinder = modelBinder;
    }

    public Object getVariable(HttpContext httpContext, Parameter parameter, FormValue annotation) {
        Object value = httpContext.getRequest().forms();

        if(!annotation.value().equals("")){
            value = httpContext.getRequest().forms().get(annotation.value());
        }

        return modelBinder.convert(value, parameter.getType());
    }
}
