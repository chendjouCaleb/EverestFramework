package Everest.Framework.Mvc.ValueResolver;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Inject.Resolve;
import Everest.Framework.Core.Inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Store and provide a {@link IAnnotationValueResolver} and {@link ITypeValueResolver}.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Singleton
public class ValueResolverProvider {
    private Logger logger = LoggerFactory.getLogger(ValueResolverProvider.class);
    private Map<Class<?>, IAnnotationValueResolver> annotationResolvers = new HashMap<>();
    private Map<Class<?>, ITypeValueResolver> typedResolvers = new HashMap<>();


    public ValueResolverProvider() {
    }

    @Resolve
    public ValueResolverProvider(List<IAnnotationValueResolver> annotationResolvers, List<ITypeValueResolver> typeResolvers){
        annotationResolvers.forEach(this::addResolver);
        typeResolvers.forEach(this::addResolver);
    }

    /**
     * Add an {@link IAnnotationValueResolver}.
     * @param variableResolver The resolver to add.
     */
    public void addResolver(IAnnotationValueResolver variableResolver){
        try{
            Class type = (Class) ((ParameterizedType) variableResolver.getClass().getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];

            if(annotationResolvers.containsKey(type)){
                logger.warn("The Value resolver '{}' of annotation '{}' is override by {}",
                        annotationResolvers.get(type), type, variableResolver.getClass());
            }
            annotationResolvers.put(type, variableResolver);

        }catch (ClassCastException e){
            throw new InvalidOperationException(variableResolver.getClass()+ " cannot added as value resolver" +
                    "Only the generic type can be added as value resolver.");
        }
    }

    /**
     * Add an {@link ITypeValueResolver}.
     * @param variableResolver The resolver to add.
     */
    public void addResolver(ITypeValueResolver variableResolver){
        try{
            Class type = (Class) ((ParameterizedType) variableResolver.getClass().getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];

            if(typedResolvers.containsKey(variableResolver.getClass())){
                logger.warn("The Value resolver '{}' of type '{}' is override by {}",
                        typedResolvers.get(type), type, variableResolver.getClass());
            }
            typedResolvers.put(type, variableResolver);

        }catch (ClassCastException e){
            throw new InvalidOperationException(variableResolver.getClass()+ " cannot added as value resolver" +
                    "Only the generic type can be added as value resolver.");
        }
    }

    /**
     * Provider a {@link IAnnotationValueResolver} corresponding to the provided class annotation.
     * @param annotation The class annotation to get a value provider.
     * @return  The {@link IAnnotationValueResolver} corresponding to the provided class annotation.
     */
    public IAnnotationValueResolver getAnnotationResolver(Class annotation){
        if(annotationResolvers.containsKey(annotation)){
            return annotationResolvers.get(annotation);
        }
        throw new NoSuchElementException("No value provider for annotation " + annotation);
    }


    /**
     * Provider a {@link ITypeValueResolver} corresponding to the provided class.
     * @param type The class to get a value provider.
     * @return  The {@link ITypeValueResolver} corresponding to the provided class.
     */
    public ITypeValueResolver getTypedResolver(Class type){
        if(typedResolvers.containsKey(type)){
            return typedResolvers.get(type);
        }
        throw new NoSuchElementException("No value provider for type " + type);
    }

    public Map<Class<?>, IAnnotationValueResolver> getAnnotationResolvers() {
        return annotationResolvers;
    }

    public Map<Class<?>, ITypeValueResolver> getTypedResolvers() {
        return typedResolvers;
    }
}
