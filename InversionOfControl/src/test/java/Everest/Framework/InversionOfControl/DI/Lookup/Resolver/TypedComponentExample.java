package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.Resolve;
import Everest.Framework.Core.Inject.UseNamed;

public class TypedComponentExample {
    @Resolve
    private String resolveField;

    @UseNamed("namedField")
    private Integer namedField;

    private Double constructorParam1;

    private Double constructorParam2;

    private String setterMethod1Param1;
    private String setterMethod1Param2;

    private String setterMethod2Param1;
    private String setterMethod2Param2;

    public TypedComponentExample(Integer namedField) {
        this.namedField = namedField;
    }

    @Resolve
    public TypedComponentExample(Double constructorParam1, @UseNamed("constructorParam2") Double constructorParam2) {
        this.constructorParam1 = constructorParam1;
        this.constructorParam2 = constructorParam2;
    }

    @Resolve
    public void setterMethod1(String setterMethod1Param1, @UseNamed("setterMethod1Param2") String setterMethod1Param2) {
        this.setterMethod1Param1 = setterMethod1Param1;
        this.setterMethod1Param2 = setterMethod1Param2;
    }

    @Resolve
    public void setterMethod2(String setterMethod2Param1, @UseNamed("setterMethod2Param2")String setterMethod2Param2) {
        this.setterMethod2Param1 = setterMethod2Param1;
        this.setterMethod2Param2 = setterMethod2Param2;
    }

    public String getResolveField() {
        return resolveField;
    }

    public Integer getNamedField() {
        return namedField;
    }

    public Double getConstructorParam1() {
        return constructorParam1;
    }

    public Double getConstructorParam2() {
        return constructorParam2;
    }

    public String getSetterMethod1Param1() {
        return setterMethod1Param1;
    }

    public String getSetterMethod1Param2() {
        return setterMethod1Param2;
    }

    public String getSetterMethod2Param1() {
        return setterMethod2Param1;
    }

    public String getSetterMethod2Param2() {
        return setterMethod2Param2;
    }
}
