package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.PutMapping;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForPut extends MappingFor<PutMapping>{

    public MappingDescriptor getDescriptor(PutMapping mapping) {
        HttpMapping httpMapping = PutMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
