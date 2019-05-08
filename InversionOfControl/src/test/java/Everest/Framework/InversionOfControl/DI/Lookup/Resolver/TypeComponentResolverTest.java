package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class TypeComponentResolverTest {
    private ComponentCollection components = new ComponentCollection();
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private LookupEngine lookupEngine;

    private Integer nameFieldValue = 123;
    private String principalStringValue = "principal string value";
    private Double principalDoubleValue = 22.02;
    private Double constructorParam2 = 23.0;
    private String setterMethod1Param2Value = "setterMethod1Param2 value";
    private String setterMethod2Param2Value = "setterMethod2Param2 value";

    @BeforeEach
    void setUp() {
        ComponentRegister register = new ComponentRegister();

        register.addTransient(TypedComponentExample.class);

        register.add().setValue(principalStringValue).asPrincipal().regist();
        register.add().setValue(nameFieldValue).setName("namedField").regist();

        register.add().setValue(principalDoubleValue).asPrincipal().regist();
        register.add().setValue(constructorParam2).setName("constructorParam2").regist();

        register.add().setValue(setterMethod1Param2Value).setName("setterMethod1Param2").regist();
        register.add().setValue(setterMethod2Param2Value).setName("setterMethod2Param2").regist();

        components = builder.build(register);

        ComponentProvider componentProvider = new ComponentProvider(new ComponentCollectionBuilder().build(register));
        lookupEngine = componentProvider.getLookupEngine();
    }

    @Test
    void resolve() {
        NamedLookup namedLookup = new NamedLookup(lookupEngine, components);
        TypeComponentResolver resolver = new TypeComponentResolver(lookupEngine, namedLookup);

        TypeComponent component = (TypeComponent) components.findByComponentType(TypedComponentExample.class);

        TypedComponentExample example = (TypedComponentExample) resolver.resolve(component);


        assertSame(principalStringValue, example.getResolveField());
        assertSame(nameFieldValue, example.getNamedField());

        assertSame(principalDoubleValue, example.getConstructorParam1());
        assertSame(constructorParam2, example.getConstructorParam2());

        assertSame(principalStringValue, example.getSetterMethod1Param1());
        assertSame(setterMethod1Param2Value, example.getSetterMethod1Param2());

        assertSame(principalStringValue, example.getSetterMethod2Param1());
        assertSame(setterMethod2Param2Value, example.getSetterMethod2Param2());

    }
}