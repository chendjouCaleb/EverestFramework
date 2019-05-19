package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import static Everest.Framework.InversionOfControl.Message.GET_COLLECTION_OF_NON_TYPED_COLLECTION;
import static org.junit.jupiter.api.Assertions.*;

class ParameterLookupTest {
    private Integer intValue1 = 10, intValue2 = 15, intValue3 = 20;
    private Long longValue1 = 10L, longValue2 = 15L, longValue3 = 20L;
    private String stringValue = "string parameter value";

    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();

    private Class<TypeForMethodInjection> type = TypeForMethodInjection.class;
    private Parameter[] parameters;
    private ParameterLookup parameterLookup;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        ComponentRegister register = new ComponentRegister();

        register.addSingleton(String.class, stringValue);
        register.add().setValue(intValue1).setName("namedField").regist();
        register.add().setValue(intValue2).regist();
        register.add().setValue(longValue1).regist();
        register.add().setValue(longValue2).regist();
        register.add().setValue(longValue3).asPrincipal().regist();

        ComponentCollection components = builder.build(register);

        ComponentProvider componentProvider = new ComponentProvider(components);

        LookupEngine lookupEngine = componentProvider.getLookupEngine();
        parameterLookup = new ParameterLookup(lookupEngine);

        parameters = type.getMethod("setDependencies", String.class, Integer.class, Long.class, List.class)
                .getParameters();
    }

    @Test
    void resolveSimpleTypedParameter(){
        String value = parameterLookup.look(parameters[0]).toString();

        assertSame(stringValue, value);
    }

    @Test
    void resolveNamedParameter() {
        Integer value = (Integer) parameterLookup.look(parameters[1]);

        assertSame(intValue1, value);
    }

    @Test
    void resolveWithPrincipal() {
        Object value = parameterLookup.look(parameters[2]);

        assertSame(longValue3, value);
    }

    @Test
    void resolveWithList() {
        List values = (List) parameterLookup.look(parameters[3]);

        assertEquals(3, values.size());
        assertTrue(values.contains(longValue1));
        assertTrue(values.contains(longValue2));
        assertTrue(values.contains(longValue3));
    }

    @Test
    void try_resolve_nonGenerics_Parameters() throws NoSuchMethodException {
        Method method = TypeForMethodInjection.class.getMethod("nonGenericsListSetter", List.class);
        Parameter parameter = method.getParameters()[0];


        Throwable th = assertThrows(IllegalStateException.class, () ->
                parameterLookup.look(parameter));

        assertEquals(GET_COLLECTION_OF_NON_TYPED_COLLECTION, th.getMessage());
    }

    static class TypeForMethodInjection {
        private String typedField;

        private Integer namedField;

        private Long withPrincipal;

        private List<Long> listField;

        public void setDependencies(String typedField, @UseNamed("namedField") Integer namedField, Long withPrincipal,
                                    List<Long> listField) {
            this.typedField = typedField;
            this.namedField = namedField;
            this.withPrincipal = withPrincipal;
            this.listField = listField;
        }

        public void nonGenericsListSetter(List list) { }

        public String getTypedField() {
            return typedField;
        }

        public Integer getNamedField() {
            return namedField;
        }

        public Long getWithPrincipal() {
            return withPrincipal;
        }

        public List<Long> getListField() {
            return listField;
        }
    }
}