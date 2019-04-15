package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.GetMapping;
import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Http.HttpMethod;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForGet extends MappingFor<GetMapping>{
    @Override
    public MappingDescriptor getDescriptor(GetMapping mapping) {
        HttpMapping httpMapping = GetMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        descriptor.setVerb(HttpMethod.GET);
        return descriptor;
    }
}
