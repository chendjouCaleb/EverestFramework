package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryProviderComponent;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;

public class FactoryProviderComponentResolver implements IComponentResolver<FactoryProviderComponent> {
    private ComponentProvider componentProvider;

    public FactoryProviderComponentResolver(ComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public Object resolve(FactoryProviderComponent component) {
        return component
                .getFactory()
                .provider(componentProvider);
    }
}
