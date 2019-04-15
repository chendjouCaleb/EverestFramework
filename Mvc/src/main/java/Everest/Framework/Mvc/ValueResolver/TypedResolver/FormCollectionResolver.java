package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.FormCollection;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class FormCollectionResolver implements ITypedValueResolver<FormCollection> {

    public FormCollection getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getRequest().forms();
    }
}
