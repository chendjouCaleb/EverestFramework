package Everest.Framework.Mvc.Routing.Mapping.MappingFor;

import Everest.Framework.Mvc.Routing.Mapping.HttpMapping;
import Everest.Framework.Mvc.Routing.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Routing.Mapping.OptionsMapping;
import org.everest.mvc.component.MappingDescriptor;

public class MappingForOptions extends MappingFor<OptionsMapping>{
    @Override
    public MappingDescriptor getDescriptor(OptionsMapping mapping) {
        HttpMapping httpMapping = OptionsMapping.class.getAnnotation(HttpMapping.class);
        MappingDescriptor descriptor = new MappingDescriptor(httpMapping);
        descriptor.setName(mapping.name());
        descriptor.setValue(mapping.value());
        return descriptor;
    }
}
