package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;

/**
 * The implementation of {@link ComponentBuilder} which build an {@link InstanceComponent}.
 *
 * @see Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentBuilder
 * @see InstanceComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class InstanceComponentBuilder implements ComponentBuilder<InstanceComponent> {

    /**
     * The builder can build a instance component if the specified descriptor have non null instance.
     * @param descriptor The descriptor which contains information about a component.
     * @return {@code true} if can build an {@link InstanceComponent}.
     */
    @Override
    public boolean canBuild(ComponentDescriptor descriptor) {
        return descriptor.getImplementationInstance() != null;
    }

    @Override
    public InstanceComponent build(ComponentDescriptor descriptor) {
        return new InstanceComponent(descriptor);
    }
}
