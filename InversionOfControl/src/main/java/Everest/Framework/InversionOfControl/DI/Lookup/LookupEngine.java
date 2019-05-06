package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.Lookup.Resolver.IComponentResolver;
import Everest.Framework.InversionOfControl.DI.Lookup.Resolver.ResolverFactory;
import Everest.Framework.InversionOfControl.IComponentProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LookupEngine {
    private ComponentCollection components;
    private ScopeComponentCache scopeComponentCache;
    private RootComponentCache rootComponentCache;
    private PrincipalLookup principalLookup;
    private IComponentProvider componentProvider;
    private ResolverFactory resolverFactory;
    private NamedLookup namedLookup;

    public LookupEngine(ComponentCollection components, ComponentProvider componentProvider,
                        RootComponentCache rootComponentCache, ScopeComponentCache scopeComponentCache) {
        this.components = components;
        this.scopeComponentCache = scopeComponentCache;
        this.rootComponentCache = rootComponentCache;
        this.principalLookup = new PrincipalLookup();

        namedLookup = new NamedLookup(this, components);
        resolverFactory = new ResolverFactory(componentProvider, this,namedLookup);
    }

    public void addSingleton() {
        components.stream().filter(c -> c.getLifetime() == ComponentLifetime.SINGLETON)
                .collect(Collectors.toList())
                .forEach(c -> rootComponentCache.put(c, lookSingleton(c)));
    }

    public void addScoped() {
        components.stream().filter(c -> c.getLifetime() == ComponentLifetime.SCOPED)
                .collect(Collectors.toList())
                .forEach(c -> scopeComponentCache.put(c, lookSingleton(c)));
    }

    //    public Object look(Class type){
//
//    }

    public Object look(String name) {
        Component component = components.findByName(name);
        if (component == null) {
            throw new NoSuchElementException(String.format("There are no components with name '%s'", name));
        }
        return look(component);
    }

    public Object look(Class type) {
        Component component = principalLookup.findPrincipal(type, components);

        return look(component);
    }

    public Object look(Component component) {
        if (component.getLifetime() == ComponentLifetime.SINGLETON) {
            return lookSingleton(component);
        } else if (component.getLifetime() == ComponentLifetime.SCOPED) {
            return lookScoped(component);
        }
        return createInstance(component);
    }

    public Object lookSingleton(Component component) {
        if (rootComponentCache.containsKey(component)) {
            return rootComponentCache.get(component);
        } else {
            Object instance = createInstance(component);
            rootComponentCache.put(component, instance);
            return instance;
        }
    }

    private Object lookScoped(Component component) {
        if (scopeComponentCache.containsKey(component)) {
            return scopeComponentCache.get(component);
        } else {
            Object instance = createInstance(component);
            scopeComponentCache.put(component, instance);
            return instance;
        }
    }

    private Object createInstance(Component component) {
        IComponentResolver resolver = resolverFactory.getResolver(component.getClass());

        return resolver.resolve(component);
    }

    public List<Object> lookComponents(Class type) {
        List<Object> instances = new ArrayList<>();
        List<Component> collection = components.listByComponentTypes(type);
        collection.forEach(c -> instances.add(look(c)));
        return instances;
    }
}
