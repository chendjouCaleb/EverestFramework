package Everest.Framework.Mvc.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Mapping.GetMapping;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;

/**
 * Converts a {@link GetMapping} annotation to a {@link MappingDescriptor}.
 * @see GetMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForGet extends MappingFor<GetMapping>{
    @Override
    public MappingDescriptor getDescriptor(GetMapping mapping) {
        HttpMapping httpMapping = GetMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.GET);
        return descriptor;
    }
}
