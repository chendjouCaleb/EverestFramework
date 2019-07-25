package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Binding.IModelBinder;
import Everest.Framework.Mvc.ValueResolver.Annotations.FormValue;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provides a value of the request form or whole form data.
 * If the form field name is not provided by annotation, the whole form data must be resolved.
 *
 * @see FormValue
 * @see Everest.Framework.Http.FormCollection
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class FormValueResolver implements IAnnotationValueResolver<FormValue> {
   private IModelBinder modelBinder;

    public FormValueResolver(IModelBinder modelBinder) {
        this.modelBinder = modelBinder;
    }

    public Object getVariable(ActionContext actionContext, Parameter parameter, FormValue annotation) {
        Object value = actionContext.getHttpContext().getRequest().forms();

        if(!annotation.value().equals("")){
            value = actionContext.getHttpContext().getRequest().forms().getFirst(annotation.value());
        }

        return modelBinder.convert(value, parameter.getType());
    }
}
