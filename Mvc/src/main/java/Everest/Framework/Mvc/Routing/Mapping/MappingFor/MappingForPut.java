package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.PutMapping;


/**
 * Converts a {@link PutMapping} annotation to a {@link MappingDescriptor}.
 * @see PutMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForPut extends MappingFor<PutMapping>{

    public MappingDescriptor getDescriptor(PutMapping mapping) {
        HttpMapping httpMapping = PutMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.PUT);
        return descriptor;
    }
}
