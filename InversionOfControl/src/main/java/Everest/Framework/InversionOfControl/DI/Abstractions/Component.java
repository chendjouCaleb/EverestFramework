package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.AfterConstructListener;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * The base class for any component type that can be registered.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public abstract class Component {

    // For test purposes.
    public Component() {}

    public Component(@Nonnull ComponentDescriptor descriptor) {
       this.setComponentType(descriptor.getComponentType());
       this.setLifetime(descriptor.getLifetime());
       this.setName(descriptor.getName());
       this.setPrincipal(descriptor.isPrincipal());
       this.setAfterConstructListeners(descriptor.getAfterConstructListeners());
    }

    /**
     * The registered type of the component.
     */
    protected Class componentType;

    /**
     * The type of the implementation of component based type.
     */
    protected Class<?> implementationType;



    /**
     * The lifetime of the component.
     */
    protected ComponentLifetime lifetime = ComponentLifetime.SINGLETON;

    /**
     * Indicates if this component is the principal component of all component which have also the same instanceType.
     */
    protected boolean isPrincipal;

    /**
     * The name of the component. This name must be unique the component container.
     */
    protected String name;

    /**
     * The required component to construction of the instance of this component.
     * The required dependencies is constructor dependencies and method factory dependencies.
     */
    private Set<Component> requiredDependencies = new HashSet<>();

    /**
     * The non required dependencies. Its can be resolved after instance creation.
     */
    private Set<Component> nonRequiredDependencies = new HashSet<>();

    /**
     * Event listeners which will be executed after the creation of component instance.
     */
    private Set<AfterConstructListener> afterConstructListeners = new HashSet<>();

    /**
     * Indicates if the component have a name.
     * @return {@code true} if the component have a name.
     */
    public boolean isNamed(){
        return name != null && !"".equals(name);
    }


    public Class getComponentType() {
        return componentType;
    }

    public Component setComponentType(Class componentType) {
        this.componentType = componentType;
        return this;
    }

    /**
     * Get the string representation of the instance object provider.
     * Is a minimal representation of to String function.
     * ex: For TypeComponent, get the implementationType
     * ex: For MethodFactoryComponent, get the provider method.
     * @return The string representation of the instance object provider.
     */
    public abstract String instanceProviderToString();

    public Class<?> getImplementationType() {
        return implementationType;
    }

    public Component setImplementationType(Class<?> implementationType) {
        this.implementationType = implementationType;
        return this;
    }


    public ComponentLifetime getLifetime() {
        return lifetime;
    }

    public Component setLifetime(ComponentLifetime lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public Component setPrincipal(boolean principal) {
        isPrincipal = principal;
        return this;
    }

    public String getName() {
        return name;
    }

    public Component setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Component> getRequiredDependencies() {
        return requiredDependencies;
    }

    public Component setRequiredDependencies(Set<Component> requiredDependencies) {
        this.requiredDependencies = requiredDependencies;
        return this;
    }

    public Set<Component> getNonRequiredDependencies() {
        return nonRequiredDependencies;
    }

    public Component setNonRequiredDependencies(Set<Component> nonRequiredDependencies) {
        this.nonRequiredDependencies = nonRequiredDependencies;
        return this;
    }

    public Set<AfterConstructListener> getAfterConstructListeners() {
        return afterConstructListeners;
    }

    public Component setAfterConstructListeners(Set<AfterConstructListener> afterConstructListeners) {
        this.afterConstructListeners = afterConstructListeners;
        return this;
    }
}
