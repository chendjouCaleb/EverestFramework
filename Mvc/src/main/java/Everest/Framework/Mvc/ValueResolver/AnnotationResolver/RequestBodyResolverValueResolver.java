package Everest.Framework.Mvc.ValueResolver.AnnotationResolver;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.ValueResolver.IAnnotationValueResolver;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.Map;

public class RequestBodyResolverValueResolver implements IAnnotationValueResolver<RequestBody> {
    private Logger logger = LoggerFactory.getLogger(RequestBodyResolverValueResolver.class);
    private RequestBodyHandler requestBodyHandler;
    private IModelValidator modelValidator;

    public RequestBodyResolverValueResolver(RequestBodyHandler requestBodyHandler, IModelValidator modelValidator) {
        this.requestBodyHandler = requestBodyHandler;
        this.modelValidator = modelValidator;
    }

    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestBody annotation) {

        Object obj = requestBodyHandler.getBody(httpContext, parameter.getType());


        Map<String, String> errors = modelValidator.validate(obj);
        BindingState state = new BindingState(obj);
        state.setFieldErrors(errors);

        if (annotation.validate() && state.getErrors().size() > 0) {
            throw new ObjectValidationException("L'object est invalide", state.getErrors());
        }
        httpContext.setBindingState(state);


        return obj;
    }

}
