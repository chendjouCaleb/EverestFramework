package Everest.Framework.Mvc.Mapping.MappingFor;

import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;

/**
 * Converts a {@link HttpMapping} annotation to a {@link MappingDescriptor}.
 * @see HttpMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForHttp extends MappingFor<HttpMapping>{
    @Override
    public MappingDescriptor getDescriptor(HttpMapping mapping) {
        MappingDescriptor descriptor = new MappingDescriptor(mapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(mapping.verbs());
        return descriptor;
    }
}
