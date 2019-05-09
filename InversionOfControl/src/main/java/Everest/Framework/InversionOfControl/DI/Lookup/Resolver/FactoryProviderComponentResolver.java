package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryProviderComponent;
import Everest.Framework.InversionOfControl.IComponentProvider;

public class FactoryProviderComponentResolver implements IComponentResolver<FactoryProviderComponent> {
    private IComponentProvider componentProvider;

    public FactoryProviderComponentResolver(IComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Object resolve(FactoryProviderComponent component) {
        return component
                .getFactory()
                .provider(componentProvider);
    }
}
