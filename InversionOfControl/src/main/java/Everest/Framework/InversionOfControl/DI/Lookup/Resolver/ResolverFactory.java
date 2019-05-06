package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.*;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.IComponentProvider;

public class ResolverFactory {
    private IComponentProvider componentProvider;
    private LookupEngine lookupEngine;
    private NamedLookup namedLookup;

    public ResolverFactory(IComponentProvider componentProvider, LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.componentProvider = componentProvider;
        this.lookupEngine = lookupEngine;
        this.namedLookup = namedLookup;
    }

    public IComponentResolver getResolver(Class<? extends Component> componentType){
        if(componentType.equals(InstanceComponent.class)){
            return new InstanceResolver();
        }else if(componentType.equals(FactoryProviderComponent.class)){
            return new FactoryProviderResolver(componentProvider);
        }else if(componentType.equals(FactoryMethodComponent.class)){
            return new FactoryMethodResolver(lookupEngine, namedLookup);
        }else if(componentType.equals(TypeComponent.class)){
            return new TypeComponentResolver(lookupEngine, namedLookup);
        }

        throw new IllegalArgumentException(String.format("There are no component resolver for type '%s'", componentType.getName()));
    }
}
