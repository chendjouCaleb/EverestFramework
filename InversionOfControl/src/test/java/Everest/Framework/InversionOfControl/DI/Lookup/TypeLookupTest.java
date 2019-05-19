package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static Everest.Framework.InversionOfControl.Message.NO_PRINCIPAL_COMPONENT;
import static org.junit.jupiter.api.Assertions.*;

class TypeLookupTest {

    private TypeLookup typeLookup;
    private Integer intValue = 10;
    private Long longValue1 = 10L, longValue2 = 20L;
    private String stringValue1 = "10L", stringValue2 = "20L";
    private

    @BeforeEach
    void setUp()  {

        ComponentRegister register = new ComponentRegister();
        register.add().setValue(intValue).regist();
        register.add().setValue(longValue1).regist();
        register.add().setValue(longValue2).asPrincipal().regist();
        register.add().setValue(stringValue1).regist();
        register.add().setValue(stringValue2).regist();

        ComponentProvider componentProvider = (ComponentProvider) register.buildComponentProvider();

        LookupEngine lookupEngine = componentProvider.getLookupEngine();
        typeLookup = new TypeLookup(lookupEngine);
    }

    @Test
    void look() {
        Object value = typeLookup.look(Integer.class);
        assertSame(intValue, value);
    }

    @Test
    void try_look_NonRegisteredType() {
        Throwable th = assertThrows(NoSuchElementException.class, () -> typeLookup.look(Double.class));
        assertEquals(String.format(Message.TYPED_COMPONENT_NOT_FOUND, Double.class.getName()), th.getMessage());
    }

    @Test
    void getPrincipalTypedComponent() {
        Object value = typeLookup.look(Long.class);
        assertSame(longValue2, value);
    }

    @Test
    void try_getMultipleType_WithoutType(){
        Throwable th = assertThrows(NoPrincipalComponentException.class, () ->typeLookup.look(String.class));
        assertEquals(String.format(NO_PRINCIPAL_COMPONENT, String.class.getName()), th.getMessage());
    }
}