package Everest.Framework.Mvc.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Mapping.PatchMapping;

/**
 * Converts a {@link PatchMapping} annotation to a {@link MappingDescriptor}.
 * @see PatchMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForPatch extends MappingFor<PatchMapping>{
    @Override
    public MappingDescriptor getDescriptor(PatchMapping mapping) {
        HttpMapping httpMapping = PatchMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.PATCH);
        return descriptor;
    }
}
