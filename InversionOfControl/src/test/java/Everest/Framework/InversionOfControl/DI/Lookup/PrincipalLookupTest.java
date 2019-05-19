package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.ManySelectableException;
import org.junit.jupiter.api.Test;

import static Everest.Framework.InversionOfControl.Message.MANY_PRINCIPAL_COMPONENT;
import static Everest.Framework.InversionOfControl.Message.NO_PRINCIPAL_COMPONENT;
import static org.junit.jupiter.api.Assertions.*;

class PrincipalLookupTest {

    @Test
    void look() {
        Integer intValue1 = 10, intValue2 = 20;
        ComponentRegister register = new ComponentRegister();
        register.add().setValue(intValue1).regist();
        register.add().setValue(intValue2).asPrincipal().regist();

        ComponentProvider componentProvider = (ComponentProvider) register.buildComponentProvider();
        PrincipalLookup lookup = new PrincipalLookup(componentProvider.getLookupEngine());

        assertSame(intValue2, lookup.look(Integer.class));
    }

    @Test
    void try_look_withoutPrincipal() {
        Integer intValue1 = 10, intValue2 = 20;
        ComponentRegister register = new ComponentRegister();
        register.add().setValue(intValue1).regist();
        register.add().setValue(intValue2).regist();

        ComponentProvider componentProvider = (ComponentProvider) register.buildComponentProvider();
        PrincipalLookup lookup = new PrincipalLookup(componentProvider.getLookupEngine());

        Throwable th = assertThrows(NoPrincipalComponentException.class, () ->lookup.look(Integer.class));
        assertEquals(String.format(NO_PRINCIPAL_COMPONENT, Integer.class.getName()), th.getMessage());
    }


    @Test
    void try_look_multiplePrincipals() {
        Integer intValue1 = 10, intValue2 = 20;
        ComponentRegister register = new ComponentRegister();
        register.add().setValue(intValue1).asPrincipal().regist();
        register.add().setValue(intValue2).asPrincipal().regist();

        ComponentProvider componentProvider = (ComponentProvider) register.buildComponentProvider();
        PrincipalLookup lookup = new PrincipalLookup(componentProvider.getLookupEngine());

        Throwable th = assertThrows(ManySelectableException.class, () -> lookup.look(Integer.class));
        assertEquals(String.format(MANY_PRINCIPAL_COMPONENT, Integer.class.getName()), th.getMessage());
    }
}