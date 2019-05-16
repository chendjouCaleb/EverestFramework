package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.IComponentScope;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.RootComponentCache;
import Everest.Framework.InversionOfControl.DI.Lookup.ScopeComponentCache;

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
    public <T> T getComponent(Class<? extends T> type) {
        return (T) lookupEngine.look(type);
    }


    public <T> T getComponent(String name, Class<? extends T> type) {
        return (T) lookupEngine.look(name);
    }


    public Object getComponent(String name) {
        return lookupEngine.look(name);
    }


    public <T> List<T> getComponents(Class<? extends T> type) {
        return (List<T>) lookupEngine.lookComponents(type);
    }

    public IComponentScope createScope() {
        return new ComponentScope(this, components, rootComponentCache);
    }

    public LookupEngine getLookupEngine() {
        return lookupEngine;
    }
}
