package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;


import java.lang.reflect.Parameter;

public class PathVariableResolverValueResolver implements IAnnotationValueResolver<PathVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, PathVariable path) {
        return httpContext.getRouteData().getParameter(path.value(), parameter.getType());

    }
}
