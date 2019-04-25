package Everest.Framework.Mvc.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Mapping.DeleteMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;

/**
 * Converts a {@link DeleteMapping} annotation to a {@link MappingDescriptor}.
 * @see DeleteMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForDelete extends MappingFor<DeleteMapping>{
    @Override
    public MappingDescriptor getDescriptor(DeleteMapping mapping) {
        HttpMapping httpMapping = mapping.annotationType().getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.DELETE);
        return descriptor;
    }
}
