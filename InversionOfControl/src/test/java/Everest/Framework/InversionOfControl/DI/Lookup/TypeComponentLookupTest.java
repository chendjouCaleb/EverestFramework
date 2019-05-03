package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.InjectionConstructorGetter;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypeComponentLookupTest {
    private InjectionConstructorGetter constructorGetter;
    private ComponentCollection components = new ComponentCollection();
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();

    @BeforeEach
    void setUp() {
        ComponentRegister register = new ComponentRegister();
        register.addTransient(StarController.class);
        register.addTransient(IStarRepository.class, StarRepository.class);
        register.addTransient(StarBuilder.class);
        components = builder.build(register);
    }

    @Test
    void look() { }


}