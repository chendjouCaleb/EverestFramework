package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.ParameterLookup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterLookupTest {
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private LookupEngine lookupEngine;
    private ComponentProvider componentProvider;

    private Class<TypeForMethodInjection> type = TypeForMethodInjection.class;
    private Parameter[] parameters;
    private ParameterLookup parameterLookup;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        ComponentRegister register = new ComponentRegister();

        register.addSingleton(String.class, "string parameter value");
        register.add().setValue(10).setName("namedField").regist();
        register.add().setValue(20.5).regist();
        register.add().setValue(30.5).asPrincipal().regist();

        ComponentCollection components = builder.build(register);

        componentProvider = new ComponentProvider(components);

        lookupEngine = componentProvider.getLookupEngine();
        parameterLookup = new ParameterLookup(lookupEngine);

        parameters = type.getMethod("setDependencies", String.class, Integer.class, Double.class)
                .getParameters();
    }

    @Test
    void resolveSimpleTypedParameter(){
        String value = parameterLookup.look(parameters[0]).toString();

        assertEquals("string parameter value", value);
    }

    @Test
    void resolveNamedParameter() {
        Integer value = (Integer) parameterLookup.look(parameters[1]);

        assertEquals(10, value.intValue());
    }

    @Test
    void resolveWithPrincipal() {
        Double value = (Double) parameterLookup.look(parameters[2]);

        assertEquals(30.5, value.doubleValue());
    }

    public static class TypeForMethodInjection {
        private String typedField;

        private Integer namedField;

        private Double withPrincipal;

        public void setDependencies(String typedField, @UseNamed("namedField") Integer namedField, Double withPrincipal) {
            this.typedField = typedField;
            this.namedField = namedField;
            this.withPrincipal = withPrincipal;
        }

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