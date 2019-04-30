package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateNameScannerTest {
    private DuplicateNameScanner duplicateNameScanner = new DuplicateNameScanner();
    @Test
    void checkDuplicateName() {
        ComponentCollection components = new ComponentCollection();
        components.add(new TypeComponent().setName("component1").setImplementationType(String.class));
        components.add(new TypeComponent().setName("component1").setImplementationType(String.class));

        assertThrows(IllegalStateException.class, () -> duplicateNameScanner.checkDuplicateName(components));
    }

    @Test
    void checkDuplicateName_WithoutDuplicate() {
        ComponentCollection components = new ComponentCollection();
        components.add(new TypeComponent().setName("component1").setImplementationType(String.class));
        components.add(new TypeComponent().setName("component").setImplementationType(String.class));

        duplicateNameScanner.checkDuplicateName(components);
    }
}