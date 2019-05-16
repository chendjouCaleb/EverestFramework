package Everest.Framework.Core;

import java.util.List;
import java.util.NoSuchElementException;

public interface IComponentProvider {
    /**
     * Get service of type T from
     * @param type The type of component to get
     * @param <T> The type of component to get
     * @return A component of type T or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component
     */
    <T> T getComponent(Class<? extends T> type);

    /**
     * Get an java.util.List of services of type T
     * @param type The type of service object to get
     * @param <T> The type of service object to get
     * @return An java.util.List of services of type T
     */
    <T> List<T> getComponents(Class<? extends T> type);

    IComponentScope createScope();
}
