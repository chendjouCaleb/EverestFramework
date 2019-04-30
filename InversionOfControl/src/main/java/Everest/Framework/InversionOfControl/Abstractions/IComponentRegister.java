package Everest.Framework.InversionOfControl.Abstractions;

import java.util.Collection;

/**
 * Specifies the contract for a collection of instance descriptors
 * The contains of this collection will the be used to build a {@link Everest.Framework.InversionOfControl.ComponentProvider}.
 *
 * @author Chendjou
 * @version 1
 * @since 28-07-2019
 */
public interface IComponentRegister extends Collection<ComponentDescriptor> {
    /**
     * Adds a scoped instance of the type specified in  componentType with an implementation
     * of the type specified in implementationType
     *
     * @param componentType The type of the instance to register
     * @param implementationType The implementation type of the instance
     */
    <T, I extends T> IComponentRegister addScoped(Class<T>  componentType, Class<I> implementationType);

    /**
     * @param  componentType The type of the instance to register.
     * @param implementationFactory The factory that creates the instance.
     */
    <T, I extends T> IComponentRegister addScoped(Class<T>  componentType, ComponentFactory<I> implementationFactory);

    /**
     * Adds a scoped instance of the type specified in  componentType
     * @param  componentType The type of the instance to register and the implementation to use.
     */
    <T> IComponentRegister addScoped(Class<T>  componentType);

    /**
     * Adds a scoped instance of the type specified in TInstance with a factory specified
     * in implementationFactory.
     *
     * @param implementationFactory The factory that creates the instance
     */
    <T> IComponentRegister addScoped(ComponentFactory<T> implementationFactory);

    /**
     * Adds a singleton instance of the type specified in TInstance with a factory specified
     * in implementationFactory
     * @param implementationFactory
     */
    <T> IComponentRegister addSingleton(ComponentFactory<T> implementationFactory);

    /**
     * Adds a singleton instance of the type specified in  componentType
     * @param  componentType The type of the instance to register and the implementation to use.
     */
    <T> IComponentRegister addSingleton(Class<T>  componentType);

    /**
     * Adds a singleton instance of the type specified in  componentType with a factory
     * specified in implementationFactory
     * @param  componentType The type of the instance to register.
     * @param implementationFactory The factory that creates the instance.
     */
    <T, I extends T> IComponentRegister addSingleton(Class<T>  componentType, ComponentFactory<I> implementationFactory);


    /**
     * Adds a singleton instance of the type specified in  componentType with an implementation
     * of the type specified in implementationType
     * @param  componentType The type of the instance to register.
     * @param implementationType The implementation type of the instance.
     */
    <T, I extends T> IComponentRegister addSingleton(Class<T>  componentType, Class<I> implementationType);

    /**
     * Adds a singleton instance of the type specified in  componentType with
     * an instance specified in implementationInstance.
     * @param  componentType The type of the instance to register.
     * @param implementationInstance The object of the instance.
     */
    <T, I extends T> IComponentRegister addSingleton(Class<T>  componentType, I implementationInstance);


    /**
     * Adds a transient instance of the type specified in  componentType
     * @param  componentType The type of the instance to register and the implementation to use.
     */
    <T> IComponentRegister addTransient(Class<T>  componentType);

    /**
     * Adds a transient instance of the type specified in  componentType with a factory
     * specified in implementationFactory
     *
     * @param  componentType          The type of the instance to register.
     * @param implementationFactory The factory that creates the instance.
     */
    <T, I extends T> IComponentRegister addTransient(Class<T>  componentType, ComponentFactory<I> implementationFactory);

    /**
     * Adds a transient instance of the type of implementationFactory
     *
     * @param implementationFactory The factory that creates the instance.
     */
    <T> IComponentRegister addTransient(ComponentFactory<T> implementationFactory);

    /**
     * Adds a transient instance of the type specified in  componentType with an implementation
     * of the type specified in implementationType
     *
     * @param  componentType       The type of the instance to register.
     * @param implementationType The implementation type of the instance.
     *
     */
    <T, I extends T> IComponentRegister addTransient(Class<T>  componentType, Class<I> implementationType);
}
