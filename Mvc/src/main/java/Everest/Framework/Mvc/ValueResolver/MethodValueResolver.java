package Everest.Framework.Mvc.ValueResolver;


import Everest.Framework.Core.Annotations;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.Action.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ComponentBuilder to handle an provide value of action method parameter.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Singleton
public class MethodValueResolver {
    private Logger logger = LoggerFactory.getLogger(MethodValueResolver.class);
    private ValueResolverProvider resolverProvider;

    public MethodValueResolver(ValueResolverProvider resolverProvider) {
        this.resolverProvider = resolverProvider;
    }

    public Object[] getVariables(Method method, ActionContext actionContext){
        Parameter[] parameters = method.getParameters();
        List<Object> variables = new ArrayList<>();

        for (Parameter parameter: parameters){
            List<Annotation> annotations = Annotations.annotatedAnnotations(parameter, ValueResolver.class);
            if(annotations.size() > 1){
                throw new ValueResolverException("The parameter " + parameter.getName() + " has multiple annotation resolver");
            }
            if(annotations.size() == 1){
                Annotation targetAnnotation = annotations.get(0);
                variables.add(resolveByAnnotation(parameter, targetAnnotation, actionContext));
            }else{
                variables.add(resolveByType(parameter, actionContext));
            }
        }

        return variables.toArray();
    }

    public Object[] getVariables(ActionContext actionContext) {
        Method method = actionContext.getActionDescriptor().getMethod();
        return getVariables(method, actionContext);
    }

    private Object resolveByAnnotation(Parameter parameter, Annotation annotation, ActionContext actionContext){
        try {
            IAnnotationValueResolver variableResolver = resolverProvider.getAnnotationResolver(annotation.annotationType());
            return variableResolver.getVariable(actionContext, parameter, annotation);
        }catch (NoSuchElementException e){
            throw new ValueResolverException("Aucun injecteur de valeur pour l'annotation: " + annotation, e);
        }

    }

    private Object resolveByType(Parameter parameter, ActionContext actionContext){
        try{
            ITypeValueResolver variableResolverByType = resolverProvider.getTypedResolver(parameter.getType());
            return variableResolverByType.getValue(actionContext, parameter);
        }catch (NoSuchElementException e){
            throw new ValueResolverException("Aucun injecteur de valeur pour le type: " + parameter.getType(), e);
        }

    }

}
