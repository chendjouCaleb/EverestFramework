package Everest.Framework.InversionOfControl;


import java.util.List;
import java.util.NoSuchElementException;

/**
 * The base interface of component instance provider.
 *
 * @author Chendjou
 * @version 2
 * @since 28-04-2019
 */
public interface IComponentProvider {
    /**
     * Gets service of type T from
     * @param type The type of component to get
     * @param <T> The type of component to get
     * @return A component of type T or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component.
     */
    <T> T GetComponent(Class<? extends T> type);

    /**
     * Get service which have the specified name.
     * @param name The name of the component.
     * @param type The type of component to get
     * @param <T> The type of component to get
     * @return A component which have the specified name or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component.
     */
    <T> T GetComponent(String name, Class<? extends T> type);

    /**
     * Get service which have the specified name
     * @param name The name of the component.
     * @return A component which have the specified name or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component.
     */
    Object GetComponent(String name);

    /**
     * Gets all services of type T from
     * @param type The type of component to get
     * @param <T> The type of component to get
     * @return All components of type T or throw NoSuchElementException
     * @throws NoSuchElementException Exception to throw if there is no such component.
     */
    <T> List<T> GetComponents(Class<? extends T> type);

}
