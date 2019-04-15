package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.PathMapping;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForPath extends MappingFor<PathMapping>{
    @Override
    public MappingDescriptor getDescriptor(PathMapping mapping) {
        HttpMapping httpMapping = PathMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
