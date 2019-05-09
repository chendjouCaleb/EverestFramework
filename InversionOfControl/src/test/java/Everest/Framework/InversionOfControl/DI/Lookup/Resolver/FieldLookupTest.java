package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.DI.Lookup.FieldLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldLookupTest {
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private LookupEngine lookupEngine;
    private ComponentProvider componentProvider;

    private Class<TypeForFieldInjection> type = TypeForFieldInjection.class;
    private Field[] fields = type.getDeclaredFields();
    private FieldLookup fieldLookup;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        ComponentRegister register = new ComponentRegister();

        register.addSingleton(String.class, "string parameter value");
        register.add().setValue(10).setName("namedField").regist();
        register.add().setValue(20.5).regist();
        register.add().setValue(30.5).asPrincipal().regist();

        register.add().setValue(20L).regist();
        register.add().setValue(30L).regist();

        ComponentCollection components = builder.build(register);

        componentProvider = new ComponentProvider(components);

        lookupEngine = componentProvider.getLookupEngine();
        fieldLookup = new FieldLookup(lookupEngine);
    }

    @Test
    void resolveSimpleTypedField(){
        String value = fieldLookup.resolve(fields[0]).toString();

        assertEquals("string parameter value", value);
    }

    @Test
    void resolveNamedField() {
        Integer value = (Integer) fieldLookup.resolve(fields[1]);

        assertEquals(10, value.intValue());
    }

    @Test
    void resolveFieldWithPrincipal() {
        Double value = (Double) fieldLookup.resolve(fields[2]);

        assertEquals(30.5, value.doubleValue());
    }


    public static class TypeForFieldInjection {
        private String typedField;

        @UseNamed("namedField")
        private Integer namedField;

        private Double withPrincipal;

        public String getTypedField() {
            return typedField;
        }

        public Integer getNamedField() {
            return namedField;
        }

        public Double getWithPrincipal() {
            return withPrincipal;
        }
    }
}