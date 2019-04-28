package Everest.Framework.InversionOfControl.Abstractions;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Core.StringUtils;
import Everest.Framework.InversionOfControl.Utils.ClassUtils;

/**
 * used to build a {@link ComponentDescriptor}.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ComponentDescriptorBuilder {
    public ComponentDescriptorBuilder(IComponentRegister componentRegister) {
        if( componentRegister == null){
            throw new NullPointerException("Cannot build a ComponentDescriptor with null ComponentRegister");
        }

    }

    private IComponentRegister componentRegister;

    private ComponentLifetime lifetime;

    private Class<?> instanceType;

    private Class<?> implementationType;

    private Object implementationInstance;

    private ComponentFactory implementationFactory;

    private String name;

    /**
     * Sets the lifetime of the component.
     * @param lifetime The lifetime of the component.
     * @return The self builder instance.
     */
    public ComponentDescriptorBuilder setLifetime(ComponentLifetime lifetime) {
        if(lifetime == null){
            throw new NullArgumentException("lifetime");
        }
        this.lifetime = lifetime;
        return this;
    }

    /**
     * Sets the instanceType of the component.
     * @param instanceType The instanceType of the component
     * @return The self builder instance.
     */

    //todo Vérifier la liasion entre le type de l'instance et celui de la factory.
    public ComponentDescriptorBuilder setInstanceType(Class<?> instanceType) {
        if(instanceType == null){
            throw new NullArgumentException("instanceType");
        }
        if(implementationType != null && instanceType.isAssignableFrom(implementationType)){
            throw new IllegalStateException(String.format("The implementation type '%s' is not assignable to instance type '%s'",
                    implementationType.getName(), instanceType.getName()));
        }

        if(implementationInstance != null && implementationInstance.getClass().isAssignableFrom(instanceType)){
            throw new IllegalStateException(String.format("The implementation instance type '%s' is not assignable to instance type '%s'",
                    implementationType.getName(), instanceType.getName()));
        }

        this.instanceType = instanceType;
        return this;
    }


    /**
     * Sets the implementation type of the component.
     * @param implementationType the implementation type of the component.
     *
     * The implementation type must be a concrete type.
     * The implementation type must be assignable to instanceType.
     * Dont set implementation type and implementation instance on same component.
     * Dont set implementation type and implementation factory on same component.
     * @return The self builder instance.
     */
    public ComponentDescriptorBuilder setImplementationType(Class<?> implementationType) {
        if(implementationType == null){
            throw new NullArgumentException("implementationType");
        }
        if(instanceType != null && instanceType.isAssignableFrom(implementationType)){
            throw new IllegalStateException(String.format("The implementation type '%s' is not assignable to instance type '%s'",
                    implementationType.getName(), instanceType.getName()));
        }

        if(implementationInstance != null){
            throw new IllegalStateException("We cannot register a component instance type and component implementation instance together.");
        }

        if(implementationFactory != null){
            throw new IllegalStateException("We cannot register a component instance type and component implementation factory together.");
        }

        if(ClassUtils.isConcrete(implementationType)){
            throw new IllegalStateException("The implementation type is not a concrete type");
        }


        this.implementationType = implementationType;
        return this;
    }


    /**
     * Sets the implementation instance of the component.
     * @param implementationInstance the implementation instance of the component.
     *
     * The implementation instance must be assignable to instanceType.
     * Dont set implementation instance and implementation type on same component.
     * Dont set implementation instance and implementation factory on same component.
     * @return The self builder instance.
     */
    public ComponentDescriptorBuilder setImplementationInstance(Object implementationInstance) {
        if(implementationInstance == null){
            throw new NullArgumentException("implementationInstance");
        }

        if(instanceType != null && implementationInstance.getClass().isAssignableFrom(implementationType)){
            throw new IllegalStateException(String.format("The implementation instance type '%s' is not assignable to instance type '%s'",
                    implementationType.getName(), instanceType.getName()));
        }

        if(implementationType != null){
            throw new IllegalStateException("We cannot register a component instance type and component implementation instance together.");
        }

        if(implementationFactory != null){
            throw new IllegalStateException("We cannot register a component instance type and component implementation factory together.");
        }


        this.implementationInstance = implementationInstance;
        return this;
    }

    /**
     * Sets the implementation factory of the component.
     * @param implementationFactory the implementation factory of the component.
     *
     * The implementation factory class must be generic.
     * The implementation factory must be assignable to instanceType.
     * Dont set implementation factory and implementation type on same component.
     * Dont set implementation factory and implementation instance on same component.
     * @return The self builder instance.
     */
    public ComponentDescriptorBuilder setImplementationFactory(ComponentFactory implementationFactory) {
        if(implementationFactory == null){
            throw new NullArgumentException("implementationFactory");
        }

        if(implementationType != null){
            throw new IllegalStateException("We cannot register a implementation factory and component implementation " +
                    "instance together.");
        }

        if(implementationInstance != null){
            throw new IllegalStateException("We cannot register a component implementation factory and component " +
                    "implementation instance together.");
        }

        this.implementationFactory = implementationFactory;
        return this;
    }

    /**
     * Set the name of the component.
     * @param name The name of the component.
     *
     * The name must be unique in component register.
     * @return The self builder instance.
     */
    public ComponentDescriptorBuilder setName(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalStateException("We cannot add component with null or empty name");
        }

        this.name = name;
        return this;
    }

    /**
     * Build the {@link ComponentDescriptor}.
     *
     * @exception InvalidOperationException If implementationType, implementationInstance and implementationFactory is null.
     * @exception InvalidOperationException If there are implementationType without instance type.
     * @return The {@link IComponentRegister} to the builder.
     */
    public IComponentRegister build() {
        if(implementationType == null && implementationInstance == null && implementationFactory == null){
            throw new InvalidOperationException("You cannot create component instance without implementationType, " +
                    "or implementationInstance, or implementationFactory");


        }

        if(implementationType != null && instanceType == null){
            throw new InvalidOperationException("If you provide implementation type, you have to set the instance type of the component");
        }


        ComponentDescriptor descriptor = new ComponentDescriptor(instanceType, implementationType);
        descriptor.setImplementationFactory(implementationFactory);
        descriptor.setName(name);
        descriptor.setLifetime(lifetime);

        componentRegister.add(descriptor);

        return componentRegister;
    }
}
