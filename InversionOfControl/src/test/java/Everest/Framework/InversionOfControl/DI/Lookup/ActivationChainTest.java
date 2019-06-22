package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ActivationChainTest {
    private TypeComponent component1;
    private TypeComponent component2;
    private InstanceComponent component3;

    @BeforeEach
    void setUp() {
        component1 = new TypeComponent();
        component1.setComponentType(Integer.class);
        component1.setImplementationType(Integer.class);

        component2 = new TypeComponent();
        component2.setComponentType(Double.class);
        component2.setImplementationType(Double.class);

        component3 = new InstanceComponent();
        component3.setComponentType(String.class);
        component3.setInstance("value");
    }

    @Test
    void addWithoutCircular() {
        ActivationChain chain = new ActivationChain();
        chain.add(component1);
        chain.add(component2);
        chain.add(component3);
    }

    @Test
    void addWithCircular() {
        ActivationChain chain = new ActivationChain();
        chain.add(component1);
        chain.add(component2);
        chain.add(component3);

        assertThrows(InvalidOperationException.class, () -> chain.add(component1))
        ;
    }
}