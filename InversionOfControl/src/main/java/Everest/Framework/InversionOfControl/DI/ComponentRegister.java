package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.Core.Exception.NotImplementedException;
import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentFactory;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.Abstractions.IComponentRegister;

/**
 * The default implementation of {@link ComponentRegister}.
 *
 * @author Chendjou
 * @version 1
 * @since 28-07-2019
 */
public class ComponentRegister extends ComponentRegisterCollectionDelegate implements IComponentRegister {
    private static final String COMPONENT_TYPE_PARAM_NAME = "instanceType";
    private static final String IMPL_TYPE_PARAM_NAME = "implementationType";
    private static final String IMPL_FACTORY_PARAM_NAME = "instanceType";


    public <T, I extends T> IComponentRegister add(Class<T> componentType, Class<I> implementationType, ComponentLifetime lifetime)
    {
        ComponentDescriptor descriptor = new ComponentDescriptor(componentType, implementationType, lifetime);
        add(descriptor);
        return this;
    }

    public <T, I extends T> IComponentRegister add(Class<T> serviceType, ComponentFactory<I> implementationFactory, ComponentLifetime lifetime)
    {
        ComponentDescriptor descriptor = new ComponentDescriptor(serviceType, implementationFactory, lifetime);
        add(descriptor);
        return this;
    }


    @Override
    public <T, I extends T> IComponentRegister addScoped(Class<T>  componentType, Class<I> implementationType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationType == null)
        {
            throw new NullArgumentException(IMPL_TYPE_PARAM_NAME);
        }

        return add(componentType, implementationType, ComponentLifetime.SCOPED);
    }



    @Override
    public <T, I extends T> IComponentRegister addScoped(Class<T> componentType, ComponentFactory<I> implementationFactory) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationFactory == null)
        {
            throw new NullArgumentException(IMPL_FACTORY_PARAM_NAME);
        }

        return add(componentType, implementationFactory, ComponentLifetime.SCOPED);
    }

    @Override
    public <T> IComponentRegister addScoped(Class<T> componentType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        return addScoped(componentType, componentType);
    }

    @Override
    public <T> IComponentRegister addScoped(ComponentFactory<T> implementationFactory) {
        throw new NotImplementedException();
    }

    @Override
    public <T> IComponentRegister addSingleton(ComponentFactory<T> implementationFactory) {
        throw new NotImplementedException();
    }

    @Override
    public <T> IComponentRegister addSingleton(Class<T>  componentType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        return addSingleton(componentType, componentType);
    }

    @Override
    public <T, I extends T> IComponentRegister addSingleton(Class<T>  componentType, ComponentFactory<I> implementationFactory) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationFactory == null)
        {
            throw new NullArgumentException(IMPL_TYPE_PARAM_NAME);
        }

        return add(componentType, implementationFactory, ComponentLifetime.SINGLETON);
    }

    @Override
    public <T, I extends T> IComponentRegister addSingleton(Class<T>  componentType, Class<I> implementationType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationType == null)
        {
            throw new NullArgumentException(IMPL_TYPE_PARAM_NAME);
        }

        return add(componentType, implementationType, ComponentLifetime.SINGLETON);
    }

    @Override
    public <T, I extends T> IComponentRegister addSingleton(Class<T> componentType, I implementationInstance) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationInstance == null)
        {
            throw new NullArgumentException(IMPL_TYPE_PARAM_NAME);
        }

        ComponentDescriptor descriptor = new ComponentDescriptor(componentType, implementationInstance);
        add(descriptor);
        return this;
    }

    @Override
    public <T> IComponentRegister addTransient(Class<T>  componentType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        return addTransient(componentType, componentType);
    }

    @Override
    public <T, I extends T> IComponentRegister addTransient(Class<T>  componentType, ComponentFactory<I> implementationFactory) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationFactory == null)
        {
            throw new NullArgumentException(IMPL_FACTORY_PARAM_NAME);
        }

        return add(componentType, implementationFactory, ComponentLifetime.TRANSIENT);
    }

    @Override
    public <T> IComponentRegister addTransient(ComponentFactory<T> implementationFactory) {
        throw new NotImplementedException();
    }

    @Override
    public <T, I extends T> IComponentRegister addTransient(Class<T>  componentType, Class<I> implementationType) {
        if (componentType == null)
        {
            throw new NullArgumentException(COMPONENT_TYPE_PARAM_NAME);
        }

        if (implementationType == null)
        {
            throw new NullArgumentException(IMPL_TYPE_PARAM_NAME);
        }

        return add(componentType, implementationType, ComponentLifetime.TRANSIENT);
    }
}
