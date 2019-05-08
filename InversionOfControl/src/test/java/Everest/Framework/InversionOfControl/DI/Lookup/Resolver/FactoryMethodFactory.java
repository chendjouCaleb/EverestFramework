package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Core.Inject.UseNamed;

public class FactoryMethodFactory {

    @Singleton
    public FactoryMethodType factoryMethodType(Integer field1, @UseNamed("field2") String field2, Double field3) {
        return new FactoryMethodType(field1, field2, field3);
    }
}
