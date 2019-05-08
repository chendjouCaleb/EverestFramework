package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstanceResolverTest {

    @Test
    void resolve() {
        Integer value = 10;
        InstanceComponent component = new InstanceComponent();
        component.setInstance(value);
        component.setComponentType(Integer.class);

        InstanceResolver resolver = new InstanceResolver();
        assertSame(value, resolver.resolve(component));
    }
}