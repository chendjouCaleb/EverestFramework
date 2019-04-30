package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentFactory;

import javax.annotation.Nonnull;

/**
 * The component based on a factory provider.
 *
 * @author Chendjou
 * @version 1
 * @since 29-04-2019
 */
public class FactoryProviderComponent extends Component{

    public FactoryProviderComponent(@Nonnull ComponentDescriptor descriptor) {
        super(descriptor);
        this.factory = descriptor.getImplementationFactory();
    }

    @Override
    public String instanceProviderToString() {
        return "Factory Provider: " + factory.toString();
    }

    /**
     * The factory of this component.
     */
    private ComponentFactory<?> factory;
}
