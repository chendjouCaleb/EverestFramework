package Everest.Framework.InversionOfControl.Abstractions;

import Everest.Framework.InversionOfControl.ComponentProvider;

/**
 * A component factory is useful when the component cannot be build using its constructor.
 * Is also useful when we want to build an instance based on application environment.
 *
 * @param <T> The type of the component that the factory will build.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public interface ComponentFactory<T> {
    T provider(ComponentProvider provider);

}
