package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.PostMapping;

/**
 * Converts a {@link PostMapping} annotation to a {@link MappingDescriptor}.
 * @see PostMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForPost extends MappingFor<PostMapping>{
    @Override
    public MappingDescriptor getDescriptor(PostMapping mapping) {
        HttpMapping httpMapping = PostMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.POST);
        return descriptor;
    }
}
