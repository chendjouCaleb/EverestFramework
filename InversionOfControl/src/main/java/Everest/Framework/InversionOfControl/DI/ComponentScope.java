package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.IComponentScope;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.RootComponentCache;
import Everest.Framework.InversionOfControl.DI.Lookup.ScopeComponentCache;

import java.util.List;

public class ComponentScope implements IComponentScope {

    private boolean isDisposed;
    private IComponentProvider componentProvider;
    private LookupEngine lookupEngine;

    public ComponentScope(ComponentProvider componentProvider, ComponentCollection componentCollection, RootComponentCache rootComponentCache) {
        this.componentProvider = componentProvider;
        this.lookupEngine = new LookupEngine(componentCollection, componentProvider, rootComponentCache, new ScopeComponentCache());
        this.lookupEngine.addScopedComponents();
    }


    public void dispose() {
isDisposed = true;
    }

    @Override
    public IComponentProvider getComponentProvider() {
        return componentProvider;
    }

    @Override
    public <T> T getComponent(Class<? extends T> type) {
        return (T) lookupEngine.look(type);
    }

    public <T> T getComponent(String name, Class<? extends T> type) {
        return (T) lookupEngine.look(name);
    }


    public Object getComponent(String name) {
        return lookupEngine.look(name);
    }

    @Override
    public <T> List<T> getComponents(Class<? extends T> type) {
        return (List<T>) lookupEngine.lookComponents(type);
    }

    @Override
    public IComponentScope createScope() {
        return componentProvider.createScope();
    }
}
