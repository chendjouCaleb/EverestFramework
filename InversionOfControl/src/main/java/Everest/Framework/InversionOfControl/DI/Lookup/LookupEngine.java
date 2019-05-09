package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.Lookup.Resolver.IComponentResolver;
import Everest.Framework.InversionOfControl.DI.Lookup.Resolver.ResolverFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LookupEngine {
    private ComponentCollection components;
    private ScopeComponentCache scopeComponentCache;
    private RootComponentCache rootComponentCache;
    private ResolverFactory resolverFactory;
    private NamedLookup namedLookup;
    private TypeLookup typeLookup;

    public LookupEngine(ComponentCollection components, ComponentProvider componentProvider,
                        RootComponentCache rootComponentCache, ScopeComponentCache scopeComponentCache) {
        this.components = components;
        this.scopeComponentCache = scopeComponentCache;
        this.rootComponentCache = rootComponentCache;

        namedLookup = new NamedLookup(this);
        typeLookup = new TypeLookup(this);
        resolverFactory = new ResolverFactory(componentProvider, this);
    }

    public void addSingletonComponents() {
        components.stream().filter(c -> c.getLifetime() == ComponentLifetime.SINGLETON)
                .collect(Collectors.toList())
                .forEach(c -> rootComponentCache.put(c, lookSingleton(c)));
    }

    public void addScopedComponents() {
        components.stream().filter(c -> c.getLifetime() == ComponentLifetime.SCOPED)
                .collect(Collectors.toList())
                .forEach(c -> scopeComponentCache.put(c, lookScoped(c)));
    }

    //    public Object look(Class type){
//
//    }

    /**
     * Finds the instance of an component by its name.
     * @param name The name of the expected component.
     * @return An instance corresponding to the component of the spécified name.
     */
    public Object look(String name) {
        try{
            return namedLookup.look(name);
        }catch (Exception e){
            throw new ResolutionException("Error during the lookup of component with name "+ name, e);
        }

    }

    /**
     * Finds the instance value of component of the specified type.
     * @param componentType The type of component to find.
     * @return The instance of the specified type.
     */
    public Object look(@Nonnull Class<?> componentType) {
        try {
            return typeLookup.look(componentType);
        }catch (Exception e){
            throw new ResolutionException("Exception was throw during the lookup of component of type " + componentType.getName(), e);
        }

    }

    /**
     * Find an instance value of the specified component.
     * @param component The component to find a value.
     * @return A instance corresponding to the specified component.
     */
    public Object look(Component component) {
        if (component.getLifetime() == ComponentLifetime.SINGLETON) {
            return lookSingleton(component);
        } else if (component.getLifetime() == ComponentLifetime.SCOPED) {
            return lookScoped(component);
        }
        return resolve(component);
    }

    public Object lookSingleton(Component component) {
        if (rootComponentCache.containsKey(component)) {
            return rootComponentCache.get(component);
        } else {
            Object instance = resolve(component);
            rootComponentCache.put(component, instance);
            return instance;
        }
    }

    private Object lookScoped(Component component) {
        if (scopeComponentCache.containsKey(component)) {
            return scopeComponentCache.get(component);
        } else {
            Object instance = resolve(component);
            scopeComponentCache.put(component, instance);
            return instance;
        }
    }

    /**
     * Creates the instance value of the specified component.
     * @param component The component to create an instance value.
     * @return The created instance.
     */
    private Object resolve(Component component) {
        try{
            IComponentResolver resolver = resolverFactory.getResolver(component.getClass());
            return resolver.resolve(component);
        }catch (Exception e){
            throw new ResolutionException("Exception was throw during the resolution of component of type " + component.getComponentType().getName(), e);
        }
    }

    /**
     * Finds a instances of all components with the specified type.
     * @param type The type of components which find instances.
     * @param <T> The type of instances.
     * @return An list of all instances.
     */
    public <T> List<T> lookComponents(Class<T> type) {
        List<T> instances = new ArrayList<>();
        List<Component> collection = components.listByComponentTypes(type);
        collection.forEach(c -> instances.add((T) look(c)));
        return instances;
    }

    public ComponentCollection getComponents() {
        return components;
    }
}
