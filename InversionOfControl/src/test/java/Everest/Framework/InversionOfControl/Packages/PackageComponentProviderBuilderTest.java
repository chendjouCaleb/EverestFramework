package Everest.Framework.InversionOfControl.Packages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageComponentProviderBuilderTest {

    @Test
    void buildComponentProvider() {
        PackageComponentProviderBuilder builder = new PackageComponentProviderBuilder("Everest.Framework.InversionOfControl.Packages");
        builder.buildComponentProvider();
    }
}