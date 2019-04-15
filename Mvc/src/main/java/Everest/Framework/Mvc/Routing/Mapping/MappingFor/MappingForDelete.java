package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.DeleteMapping;
import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForDelete extends MappingFor<DeleteMapping>{
    @Override
    public MappingDescriptor getDescriptor(DeleteMapping mapping) {
        HttpMapping httpMapping = mapping.annotationType().getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
