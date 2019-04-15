package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;

/**
 * The base class to convert a HttpMapping annotation to a {@link MappingDescriptor} instance.
 * @see MappingDescriptor
 * @see Everest.Framework.Mvc.Routing.Mapping.HttpMapping
 * @param <T> The type of the annotation to convert.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public abstract class MappingFor<T extends Annotation> {
    private Class<T> mapper;

    /**
     * The converter method.
     * @param mapping The annotation to convert.
     * @return The {@link MappingDescriptor} based on the provided annotation.
     */
    public abstract MappingDescriptor getDescriptor(T mapping);


    public MappingFor(){
        mapper = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getMapper() {
        return mapper;
    }
}
