package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.RootComponentCache;
import Everest.Framework.InversionOfControl.DI.Lookup.ScopeComponentCache;
import Everest.Framework.InversionOfControl.IComponentProvider;
import Everest.Framework.InversionOfControl.IComponentScope;

import java.util.List;

/**
 * The default implementation of {@link IComponentProvider}.
 * @see IComponentProvider
 *
 * @author Chendjou
 * @version 1
 * @since 01-05-2019
 */
public class ComponentProvider implements IComponentProvider {
    private LookupEngine lookupEngine;
    private RootComponentCache rootComponentCache;
    private ComponentCollection components;

    public ComponentProvider(ComponentCollection components) {
        this.components = components;
        rootComponentCache = new RootComponentCache();
        this.lookupEngine = new LookupEngine(components, this, rootComponentCache, new ScopeComponentCache());
        this.lookupEngine.addSingletonComponents();
    }

    @Override
    public <T> T GetComponent(Class<? extends T> type) {
        return (T) lookupEngine.look(type);
    }

    @Override
    public <T> T GetComponent(String name, Class<? extends T> type) {
        return (T) lookupEngine.look(name);
    }

    @Override
    public Object GetComponent(String name) {
        return lookupEngine.look(name);
    }

    @Override
    public <T> List<T> GetComponents(Class<? extends T> type) {
        return (List<T>) lookupEngine.lookComponents(type);
    }

    public IComponentScope createScope() {
        return new ComponentScope(this, components, rootComponentCache);
    }

    public LookupEngine getLookupEngine() {
        return lookupEngine;
    }
}
