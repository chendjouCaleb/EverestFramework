package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryProviderComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.RootComponentCache;
import Everest.Framework.InversionOfControl.DI.Lookup.ScopeComponentCache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResolverFactoryTest {

    @Test
    void getResolver() {
        ComponentCollection componentCollection = new ComponentCollection();
        ComponentProvider componentProvider = new ComponentProvider(componentCollection);
        RootComponentCache rootComponentCache = new RootComponentCache();
        ScopeComponentCache scopeComponentCache = new ScopeComponentCache();
        LookupEngine lookupEngine = new LookupEngine(componentCollection, componentProvider, rootComponentCache, scopeComponentCache);
        ResolverFactory factory = new ResolverFactory(componentProvider, lookupEngine, new NamedLookup(lookupEngine, componentCollection));

        assertEquals(FactoryMethodResolver.class, factory.getResolver(FactoryMethodComponent.class).getClass());
        assertEquals(InstanceResolver.class, factory.getResolver(InstanceComponent.class).getClass());
        assertEquals(FactoryProviderResolver.class, factory.getResolver(FactoryProviderComponent.class).getClass());
        assertEquals(TypeComponentResolver.class, factory.getResolver(TypeComponent.class).getClass());
    }
}