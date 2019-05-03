package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.ScopeComponentCache;
import Everest.Framework.InversionOfControl.IComponentProvider;
import Everest.Framework.InversionOfControl.IComponentScope;

import javax.annotation.Nonnull;
import java.util.List;

public class ComponentScope implements IComponentScope {

    private boolean isDisposed;
    private IComponentProvider componentProvider;
    private LookupEngine lookupEngine;

    public ComponentScope(IComponentProvider componentProvider, LookupEngine lookupEngine) {
        this.componentProvider = componentProvider;
        this.lookupEngine = lookupEngine;
    }

    @Override
    public void dispose() {
isDisposed = true;
    }

    @Override
    public IComponentProvider getComponentProvider() {
        return null;
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
}
