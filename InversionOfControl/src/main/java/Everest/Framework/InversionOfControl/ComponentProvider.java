package Everest.Framework.InversionOfControl;


import java.util.NoSuchElementException;

/**
 * The base interface of component instance provider.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public interface ComponentProvider {
    /**
     * Get service of type T from
     * @param type The type of component to get
     * @param <T> The type of component to get
     * @return A component of type T or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component
     */
    <T> T GetComponent(Class<? extends T> type);
}
