package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.OptionsMapping;

/**
 * Converts a {@link OptionsMapping} annotation to a {@link MappingDescriptor}.
 * @see OptionsMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForOptions extends MappingFor<OptionsMapping>{
    @Override
    public MappingDescriptor getDescriptor(OptionsMapping mapping) {
        HttpMapping httpMapping = OptionsMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.OPTIONS);
        return descriptor;
    }
}
