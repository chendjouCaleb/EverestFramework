package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForHttp extends MappingFor<HttpMapping>{
    @Override
    public MappingDescriptor getDescriptor(HttpMapping mapping) {
        MappingDescriptor descriptor = new MappingDescriptor(mapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
