package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import com.sun.xml.internal.ws.api.wsdl.parser.ServiceDescriptor;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Constructor constructor;

    /**
     * The methods that will be executed after the construction of the component instance.
     */
    private List<Method> postConstructMethods = new ArrayList<>();

    /**
     * The setter methods used to inject dependencies.
     */
    private Set<Method> setterInjector = new HashSet<>();

}
