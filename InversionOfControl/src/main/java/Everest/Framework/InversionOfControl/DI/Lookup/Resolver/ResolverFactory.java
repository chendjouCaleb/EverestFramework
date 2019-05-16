package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.*;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;


public class ResolverFactory {
    private ComponentProvider componentProvider;
    private LookupEngine lookupEngine;

    public ResolverFactory(ComponentProvider componentProvider, LookupEngine lookupEngine) {
        this.componentProvider = componentProvider;
        this.lookupEngine = lookupEngine;
    }

    public IComponentResolver getResolver(Class<? extends Component> componentType){
        if(componentType.equals(InstanceComponent.class)){
            return new InstanceComponentResolver();
        }else if(componentType.equals(FactoryProviderComponent.class)){
            return new FactoryProviderComponentResolver(componentProvider);
        }else if(componentType.equals(FactoryMethodComponent.class)){
            return new FactoryMethodComponentResolver(lookupEngine);
        }else if(componentType.equals(TypeComponent.class)){
            return new TypeComponentResolver(lookupEngine);
        }

        throw new IllegalArgumentException(String.format("There are no component resolver for type '%s'", componentType.getName()));
    }
}
