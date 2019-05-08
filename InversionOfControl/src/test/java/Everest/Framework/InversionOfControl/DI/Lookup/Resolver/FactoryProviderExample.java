package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.Abstractions.ComponentFactory;
import Everest.Framework.InversionOfControl.IComponentProvider;

public class FactoryProviderExample implements ComponentFactory<FactoryProviderExample> {
    private Integer field1;
    private String field2;
    private Double field3;

    public FactoryProviderExample(Integer field1, String field2, Double field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public Integer getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public Double getField3() {
        return field3;
    }

    public FactoryProviderExample() {
    }

    @Override
    public FactoryProviderExample provider(IComponentProvider provider) {
        Integer field1 = provider.GetComponent(Integer.class);
        String field2 = provider.GetComponent(String.class);
        Double field3 = provider.GetComponent(Double.class);
        return new FactoryProviderExample(field1, field2, field3);
    }
}
