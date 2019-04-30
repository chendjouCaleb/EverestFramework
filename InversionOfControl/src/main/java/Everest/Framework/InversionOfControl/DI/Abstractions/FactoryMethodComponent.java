package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

/**
 * The component based on a factory method.
 *
 * @author Chendjou
 * @version 1
 * @since 29-04-2019
 */
public class FactoryMethodComponent extends Component {

    /**
     * Creates a new {@link FactoryMethodComponent} component.
     * @param factoryMethod The method which will be used to create an instance of the class component.
     * @param typeComponent The component qui contains the implementation of the factoryMethod.
     * @throws IllegalStateException If typeComponent is not a singleton component.
     */

    public FactoryMethodComponent(@Nonnull Method factoryMethod, @Nonnull TypeComponent typeComponent) {
        if(typeComponent.lifetime != ComponentLifetime.SINGLETON){
            throw new IllegalStateException(String.format("The method '%s' of factory component can be use " +
                    "only in singleton component", factoryMethod.getName()));
        }

        this.factoryMethod = factoryMethod;
        this.typeComponent = typeComponent;
    }

    /**
     * The method used to created an instance of the component.
     */
    private Method factoryMethod;

    /**
     * The base component which implementation type contains the method of this component.
     */
    private TypeComponent typeComponent;
}
