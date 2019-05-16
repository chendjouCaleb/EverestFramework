package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentFactory;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

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
        try {
            Method providerMethod = this.factory.getClass().getMethod("provider", IComponentProvider.class);
            this.componentType = providerMethod.getReturnType();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String instanceProviderToString() {
        return "Factory Provider: " + factory.toString();
    }

    /**
     * The factory of this component.
     */
    private ComponentFactory<?> factory;

    public ComponentFactory<?> getFactory() {
        return factory;
    }

    public FactoryProviderComponent setFactory(ComponentFactory<?> factory) {
        this.factory = factory;
        return this;
    }

    @Override
    public String toString() {
        return "FactoryProviderComponent{" +
                "factory=" + factory +
                ", componentType=" + componentType +
                ", implementationType=" + implementationType +
                ", lifetime=" + lifetime +
                ", isPrincipal=" + isPrincipal +
                ", name='" + name + '\'' +
                '}';
    }
}
