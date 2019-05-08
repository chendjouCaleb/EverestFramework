package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodResolverTest {
    private ComponentCollection components = new ComponentCollection();
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private LookupEngine lookupEngine;

    private Integer field1Value = 123;
    private String field2Value = "field2 value";
    private Double field3Value = 22.02;

    @BeforeEach
    void setUp() {
        ComponentRegister register = new ComponentRegister();

        register.addTransient(FactoryMethodFactory.class);

        register.add().setValue(field1Value).regist();
        register.add().setValue(field2Value).setName("field2").regist();
        register.add().setValue(field3Value).regist();

        components = builder.build(register);

        ComponentProvider componentProvider = new ComponentProvider(new ComponentCollectionBuilder().build(register));
        lookupEngine = componentProvider.getLookupEngine();
    }

    @Test
    void resolve() {
        FactoryMethodComponent component = (FactoryMethodComponent) components.findByComponentType(FactoryMethodType.class);
        FactoryMethodResolver resolver = new FactoryMethodResolver(lookupEngine, new NamedLookup(lookupEngine, components));

        FactoryMethodType instance = (FactoryMethodType) resolver.resolve(component);

        assertSame(field1Value, instance.getField1());
        assertSame(field2Value, instance.getField2());
        assertSame(field3Value, instance.getField3());

    }
}