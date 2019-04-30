package Everest.Framework.InversionOfControl.Abstractions;

import java.util.HashSet;
import java.util.Set;

/**
 * Provide a description of a stored component instance.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ComponentDescriptor {
    private static final String NULL_INSTANCE_TYPE_MESSAGE = "componentType cannot be null";
    private static final String NULL_IMPLEMENTATION_TYPE_MESSAGE = "implementation cannot be null";
    private static final String NULL_FACTORY_MESSAGE = "Factory cannot be null";
    /**
     * Initializes a new instance of {@link ComponentDescriptor "} with the specified implementationType
     * @param componentType" The type of the instance.
     * @param implementationType" The type implementing the instance.
     * @param lifetime" The {@link ComponentLifetime}" of the instance.
     */
    public ComponentDescriptor(Class componentType, Class implementationType, ComponentLifetime lifetime) {
        this(componentType, lifetime);

        if (componentType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationType == null)
        {
            throw new NullPointerException(NULL_IMPLEMENTATION_TYPE_MESSAGE);
        }

        this.implementationType = implementationType;
    }


    /**
     * Initializes a new instance of {@link  ComponentDescriptor}
     * with the specified instance.
     * as a {@link  ComponentLifetime#SINGLETON} .
     * @param componentType The Type of the instance.
     * @param instance The instance implementing the instance.
     */
    public ComponentDescriptor(Class componentType, Object instance){
        this(componentType, ComponentLifetime.SINGLETON);

        if (componentType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (instance == null)
        {
            throw new NullPointerException("You must provider a non null instance");
        }

        implementationInstance = instance;
    }

    /**
     * Initializes a new instance of {@link ComponentDescriptor}" with the specified factory.

     * @param componentType The Type of the instance.
     * @param factory A factory used for creating instance instances.
     * @param lifetime The {@link ComponentLifetime} of the instance.
     */
    public ComponentDescriptor(Class componentType, ComponentFactory factory, ComponentLifetime lifetime)
    {
        this(componentType, lifetime);

        if (componentType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (factory == null)
        {
            throw new NullPointerException(NULL_FACTORY_MESSAGE);
        }

        this.implementationFactory = factory;
    }

    private ComponentDescriptor(Class componentType, ComponentLifetime lifetime)
    {
        this.lifetime = lifetime;
        this.componentType = componentType;
    }

    private ComponentLifetime lifetime;

    private Class componentType;

    private Class implementationType;

    private Object implementationInstance;

    private ComponentFactory implementationFactory;

    /**
     * The name of the component. This name must be unique the component container.
     */
    private String name;

    /**
     * Indicates if this component is the principal component of all component which have also the same componentType.
     */
    private boolean isPrincipal;

    /**
     * Event listeners which will be executed after the creation of component instance.
     */
    private Set<AfterConstructListener> afterConstructListeners = new HashSet<>();



    public Class getImplementationType()
    {
        return implementationType;
//        if (implementationType != null)
//        {
//            return implementationType;
//        }
//        else if (implementationInstance != null)
//        {
//            return implementationInstance.getClass();
//        }
//        else if (implementationFactory != null)
//
//            throw new IllegalStateException("ImplementationType, ImplementationInstance or ImplementationFactory must be non null");
//        return null;
    }

    @Override
    public String toString() {
        return "ComponentDescriptor{" +
                "lifetime=" + lifetime +
                ", componentType=" + componentType +
                ", implementationType=" + implementationType +
                ", implementationInstance=" + implementationInstance +
                ", ImplementationFactory=" + implementationFactory +
                '}';
    }


    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * componentType and implementationType
     * and the  {@link ComponentLifetime#TRANSIENT} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationType The type of the implementation.
     * @return A new instance of {@link ComponentDescriptor} ComponentDescriptor"
     */
    public static ComponentDescriptor Transient(Class instanceType, Class implementationType)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationType == null)
        {
            throw new NullPointerException(NULL_IMPLEMENTATION_TYPE_MESSAGE);
        }

        return Describe(instanceType, implementationType,   ComponentLifetime.TRANSIENT);
    }

    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     *  componentType, implementationFactory,
     * and the {@link ComponentLifetime#TRANSIENT} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationFactory A factory to create new instances of the instance implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Transient(Class instanceType, ComponentFactory implementationFactory)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationFactory == null)
        {
            throw new NullPointerException(NULL_FACTORY_MESSAGE);
        }

        return Describe(instanceType, implementationFactory,   ComponentLifetime.TRANSIENT);
    }


    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     *  componentType" and  implementationType.
     * and the {@link ComponentLifetime#SCOPED} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationType The type of the implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Scoped(Class instanceType, Class implementationType)
    {
        return Describe(instanceType, implementationType,   ComponentLifetime.SCOPED);
    }

    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * componentType,  implementationFactory,
     * and the {@link ComponentLifetime#SCOPED} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationFactory A factory to create new instances of the instance implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Scoped(Class instanceType, ComponentFactory implementationFactory)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationFactory == null)
        {
            throw new NullPointerException(NULL_FACTORY_MESSAGE);
        }
        return Describe(instanceType, implementationFactory,   ComponentLifetime.SCOPED);
    }


    /**
     * Creates an instance of {@link ComponentDescriptor}. with the specified
     * componentType,  implementationFactory,
     * and the {@link ComponentLifetime#SINGLETON} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationType The type of the implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Singleton(Class instanceType, Class implementationType)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationType == null)
        {
            throw new NullPointerException(NULL_IMPLEMENTATION_TYPE_MESSAGE);
        }

        return Describe(instanceType, implementationType,   ComponentLifetime.SINGLETON);
    }

    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * componentType, implementationFactory,
     * and the {@link ComponentLifetime#SINGLETON} lifetime.

     * @param instanceType The type of the instance.
     * @param implementationFactory A factory to create new instances of the instance implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Singleton(Class instanceType, ComponentFactory implementationFactory)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationFactory == null)
        {
            throw new NullPointerException(NULL_FACTORY_MESSAGE);
        }

        return Describe(instanceType, implementationFactory,   ComponentLifetime.SINGLETON);
    }


    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * InstanceType, implementationInstance,
     * and the {@link ComponentLifetime#SINGLETON}lifetime.

     * @param instanceType The type of the instance.
     * @param implementationInstance The instance of the implementation.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Singleton(Class instanceType, Object implementationInstance)
    {
        if (instanceType == null)
        {
            throw new NullPointerException(NULL_INSTANCE_TYPE_MESSAGE);
        }

        if (implementationInstance == null)
        {
            throw new NullPointerException("You must provide a non null implementation of componentType");
        }

        return new ComponentDescriptor(instanceType, implementationInstance);
    }

    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * componentType, implementationType, and lifetime.

     * @param InstanceType The type of the instance.
     * @param implementationType The type of the implementation.
     * @param lifetime The lifetime of the instance.
     * @return A new instance of {@link ComponentDescriptor}
     */
    public static ComponentDescriptor Describe(Class InstanceType, Class implementationType, ComponentLifetime lifetime)
    {
        return new ComponentDescriptor(InstanceType, implementationType, lifetime);
    }

    /**
     * Creates an instance of {@link ComponentDescriptor} with the specified
     * componentType, implementationFactory, and lifetime.

     * @param InstanceType The type of the instance.
     * @param implementationFactory A factory to create new instances of the instance implementation.
     * @param lifetime The lifetime of the instance.
     * @return A new instance of {@link ComponentDescriptor}.
     */
    public static ComponentDescriptor Describe(Class InstanceType, ComponentFactory implementationFactory, ComponentLifetime lifetime)
    {
        return new ComponentDescriptor(InstanceType, implementationFactory, lifetime);
    }

    public ComponentLifetime getLifetime() {
        return lifetime;
    }

    public ComponentDescriptor setLifetime(ComponentLifetime lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public Class getComponentType() {
        return componentType;
    }

    public ComponentDescriptor setComponentType(Class componentType) {
        this.componentType = componentType;
        return this;
    }

    public ComponentDescriptor setImplementationType(Class implementationType) {
        this.implementationType = implementationType;
        return this;
    }

    public Object getImplementationInstance() {
        return implementationInstance;
    }

    public ComponentDescriptor setImplementationInstance(Object implementationInstance) {
        this.implementationInstance = implementationInstance;
        return this;
    }

    public ComponentFactory getImplementationFactory() {
        return implementationFactory;
    }

    public ComponentDescriptor setImplementationFactory(ComponentFactory implementationFactory) {
        this.implementationFactory = implementationFactory;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComponentDescriptor setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public ComponentDescriptor setPrincipal(boolean principal) {
        isPrincipal = principal;
        return this;
    }

    public Set<AfterConstructListener> getAfterConstructListeners() {
        return afterConstructListeners;
    }

    public ComponentDescriptor setAfterConstructListeners(Set<AfterConstructListener> afterConstructListeners) {
        this.afterConstructListeners = afterConstructListeners;
        return this;
    }
}
