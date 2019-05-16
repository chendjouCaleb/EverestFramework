package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The component registered by a implementation type.
 *
 * @author Chendjou
 * @version 1
 * @since 29-04-2019
 */
public class TypeComponent extends Component{

    public TypeComponent(){}
    public TypeComponent(@Nonnull ComponentDescriptor descriptor){
        super(descriptor);
        this.setImplementationType( descriptor.getImplementationType());
    }

    @Override
    public String instanceProviderToString() {
        return String.format("Type component: %s", implementationType.getName());
    }

    /**
     * The constructor of the implementation type.
     * The implementation type can be have many constructor.
     * This constructor is one who have selected for instance creation.
     */
    private Constructor injectionConstructor;

    /**
     * The methods that will be executed after the initialisation of the component instance.
     */
    private List<Method> postInitMethods = new ArrayList<>();

    /**
     * The injection methods used to inject dependencies.
     */
    private List<Method> injectionMethods;

    /**
     * The injection fields used to inject dependencies.
     */
    private List<Field> injectionFields;

    public Constructor getInjectionConstructor() {
        return injectionConstructor;
    }

    public TypeComponent setInjectionConstructor(Constructor injectionConstructor) {
        this.injectionConstructor = injectionConstructor;
        return this;
    }

    public List<Method> getPostInitMethods() {
        return postInitMethods;
    }

    public TypeComponent setPostInitMethods(List<Method> postInitMethods) {
        this.postInitMethods = postInitMethods;
        return this;
    }

    public List<Method> getInjectionMethods() {
        return injectionMethods;
    }

    public TypeComponent setInjectionMethods(List<Method> injectionMethods) {
        this.injectionMethods = injectionMethods;
        return this;
    }

    public List<Field> getInjectionFields() {
        return injectionFields;
    }

    public TypeComponent setInjectionFields(List<Field> injectionFields) {
        this.injectionFields = injectionFields;
        return this;
    }

    @Override
    public String toString() {
        return "TypeComponent{" +
                "componentType=" + componentType.getName() +
                ", implementationType=" + implementationType.getName() +
                ", lifetime=" + lifetime +
                ", isPrincipal=" + isPrincipal +
                ", name='" + name + '\'' +
                '}';
    }
}
