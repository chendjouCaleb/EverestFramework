package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.PostMapping;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForPost extends MappingFor<PostMapping>{
    @Override
    public MappingDescriptor getDescriptor(PostMapping mapping) {
        HttpMapping httpMapping = PostMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
