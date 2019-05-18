package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static Everest.Framework.InversionOfControl.Message.NAMED_COMPONENT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NamedLookupTest {

    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private NamedLookup namedLookup;
    private Integer value = 10;

    @BeforeEach
    void setUp()  {
        ComponentRegister register = new ComponentRegister();

        register.addSingleton(String.class, "string parameter value");
        register.add().setValue(value).setName("namedValue").regist();

        ComponentCollection components = builder.build(register);
        ComponentProvider componentProvider = new ComponentProvider(components);
        LookupEngine lookupEngine = componentProvider.getLookupEngine();

        namedLookup = new NamedLookup(lookupEngine);
    }

    @Test
    void look() {
        assertEquals(value, namedLookup.look("namedValue"));
    }

    @Test
    void try_getUnExistingNamedComponent() {
        Throwable th = assertThrows(NoSuchElementException.class, () -> namedLookup.look("name"));

        assertEquals(String.format(NAMED_COMPONENT_NOT_FOUND, "name"), th.getMessage());
    }
}