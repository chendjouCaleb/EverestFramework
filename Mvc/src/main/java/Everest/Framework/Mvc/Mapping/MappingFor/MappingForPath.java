package Everest.Framework.Mvc.Mapping.MappingFor;

import Everest.Framework.Http.HttpMethod;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Mapping.PathMapping;

/**
 * Converts a {@link PathMapping} annotation to a {@link MappingDescriptor}.
 * @see PathMapping
 * @see MappingDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class MappingForPath extends MappingFor<PathMapping>{
    @Override
    public MappingDescriptor getDescriptor(PathMapping mapping) {
        HttpMapping httpMapping = PathMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.PATH);
        return descriptor;
    }
}
