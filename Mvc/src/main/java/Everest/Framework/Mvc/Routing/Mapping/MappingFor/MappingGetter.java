package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.MappingException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * A factory to provide a MappingDescriptor for a HttpMapping annotation.
 * @see MappingDescriptor
 * @see Everest.Framework.Mvc.Routing.Mapping.HttpMapping
 * @see MappingFor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingGetter {
    private List<MappingFor> mappingHandlers = new ArrayList<>();

    public MappingGetter(){
        mappingHandlers.add(new MappingForDelete());
        mappingHandlers.add(new MappingForPost());
        mappingHandlers.add(new MappingForGet());
        mappingHandlers.add(new MappingForPut());
        mappingHandlers.add(new MappingForHttp());
        mappingHandlers.add(new MappingForPath());
        mappingHandlers.add(new MappingForOptions());
    }

    /**
     * Find an returns a MappingDescriptor corresponding to {@param annotation}.
     * @param annotation The provided annotation.
     * @return The corresponding mapping descriptor.
     */
    public MappingDescriptor mappingDescriptor(Annotation annotation){
        MappingFor mappingFor = mappingHandlers.stream()
                .filter(mappingFor1 -> mappingFor1.getMapper().equals(annotation.annotationType()))
                .findFirst().orElse(null);

        if(mappingFor == null){
            throw new MappingException("No mapping descriptor found for annotation: " + annotation.annotationType().getSimpleName());
        }
        return mappingFor.getDescriptor(annotation);
    }
}
