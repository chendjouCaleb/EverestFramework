package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryProviderComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;

/**
 * The implementation of {@link ComponentBuilder} which build a {@link FactoryProviderComponent}.
 *
 * @see Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentBuilder
 * @see FactoryProviderComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class FactoryProviderComponentBuilder implements ComponentBuilder<FactoryProviderComponent> {

    /**
     * The builder can build a {@link FactoryProviderComponent} if the specified descriptor have non null factory provider.
     * @param descriptor The descriptor which contains information about a component.
     * @return {@code true} if can build an {@link FactoryProviderComponent}.
     */
    @Override
    public boolean canBuild(ComponentDescriptor descriptor) {
        return descriptor.getImplementationFactory() != null;
    }

    @Override
    public FactoryProviderComponent build(ComponentDescriptor descriptor) {
        return new FactoryProviderComponent(descriptor);
    }
}
