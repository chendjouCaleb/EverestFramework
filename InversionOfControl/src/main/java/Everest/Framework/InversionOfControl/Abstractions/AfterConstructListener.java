package Everest.Framework.InversionOfControl.Abstractions;


import Everest.Framework.Core.IComponentProvider;

/**
 * The method of this interface will be invoked after to construct of the targeted component.
 *
 * @param <T> The type of the created component.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public interface AfterConstructListener<T> {
    void afterConstruct(T instance, IComponentProvider provider);
}
