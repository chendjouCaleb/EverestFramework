package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalDuplicateGetterTest {
    private PrincipalDuplicateGetter getter = new PrincipalDuplicateGetter();
    @Test
    void checkDuplicatePrincipal() {
        ComponentCollection components = new ComponentCollection();
        components.add(new TypeComponent().setPrincipal(true).setComponentType(String.class).setImplementationType(String.class));
        components.add(new TypeComponent().setPrincipal(true).setComponentType(String.class).setImplementationType(String.class));
        components.add(new TypeComponent().setPrincipal(true).setComponentType(Integer.class).setImplementationType(Integer.class));

        assertThrows(IllegalStateException.class, () -> getter.checkDuplicatePrincipal(components));
    }

    @Test
    void checkDuplicatePrincipal_WithoutDuplicate() {
        ComponentCollection components = new ComponentCollection();
        components.add(new TypeComponent().setPrincipal(true).setComponentType(String.class).setImplementationType(String.class));
        components.add(new TypeComponent().setPrincipal(true).setComponentType(Integer.class).setImplementationType(Integer.class));

        getter.checkDuplicatePrincipal(components);
    }
}