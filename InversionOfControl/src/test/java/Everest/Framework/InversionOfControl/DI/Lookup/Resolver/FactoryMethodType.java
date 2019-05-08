package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

public class FactoryMethodType {
    private Integer field1;
    private String field2;
    private Double field3;

    public FactoryMethodType(Integer field1, String field2, Double field3) {
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
}
