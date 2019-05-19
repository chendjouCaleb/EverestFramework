package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static Everest.Framework.InversionOfControl.Message.GET_COLLECTION_OF_NON_TYPED_COLLECTION;
import static org.junit.jupiter.api.Assertions.*;

class FieldLookupTest {
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();

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

        ComponentProvider componentProvider = new ComponentProvider(components);

        LookupEngine lookupEngine = componentProvider.getLookupEngine();
        fieldLookup = new FieldLookup(lookupEngine, new CollectionLookup(lookupEngine));
    }

    @Test
    void resolveSimpleTypedField() throws NoSuchFieldException {
        Field typedField = type.getDeclaredField("typedField");
        String value = fieldLookup.look(typedField).toString();

        assertEquals("string parameter value", value);
    }

    @Test
    void resolveNamedField() throws NoSuchFieldException {
        Field namedField = type.getDeclaredField("namedField");
        Integer value = (Integer) fieldLookup.look(namedField);

        assertEquals(10, value.intValue());
    }

    @Test
    void resolveCollectionField() throws NoSuchFieldException {
        Field collectionField = type.getDeclaredField("collectionField");
        List collection = (List) fieldLookup.look(collectionField);

        assertTrue(collection.contains(20L));
        assertTrue(collection.contains(30L));
    }

    @Test
    void try_resolve_nonTypedCollectionField() throws NoSuchFieldException {
        Field nonTypedCollectionField = type.getDeclaredField("nonTypedCollectionField");
        Throwable th = assertThrows(IllegalStateException.class, () ->
                fieldLookup.look(nonTypedCollectionField));

        assertEquals(GET_COLLECTION_OF_NON_TYPED_COLLECTION, th.getMessage());
    }

    @Test
    void resolveFieldWithPrincipal() {
        Double value = (Double) fieldLookup.look(fields[2]);

        assertEquals(30.5, value.doubleValue());
    }


    public static class TypeForFieldInjection {
        public String typedField;

        @UseNamed("namedField")
        private Integer namedField;

        private Double withPrincipal;

        public List<Long> collectionField;

        private List nonTypedCollectionField;

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