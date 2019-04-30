package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

/**
 * The base interface of classes which can build a {@link Everest.Framework.InversionOfControl.DI.Abstractions.Component}.
 *
 * @param <T> The type of the component build by the builder.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public interface ComponentBuilder<T extends Component> {
    /**
     * Checks if the builder can build a ComponentBuilder based on a specified component descriptor.
     * @param descriptor The descriptor which contains information about a component.
     * @return {@code} if the builder can build the component.
     */
    boolean canBuild(ComponentDescriptor descriptor);

    /**
     * Build the component.
     * @param descriptor The descriptor which contains information about a component.
     * @return The built component.
     */
    T build(ComponentDescriptor descriptor);
}
