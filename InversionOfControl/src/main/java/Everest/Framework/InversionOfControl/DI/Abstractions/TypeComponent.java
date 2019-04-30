package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
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

    public TypeComponent(@Nonnull ComponentDescriptor descriptor){
        super(descriptor);
        this.implementationType = descriptor.getComponentType();
    }

    /**
     * The constructor of the implementation type.
     * The implementation type can be have many constructor.
     * This constructor is one who have selected for instance creation.
     */
    private Constructor injectionMethod;

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
    private List<Method> injectionFields;


}
