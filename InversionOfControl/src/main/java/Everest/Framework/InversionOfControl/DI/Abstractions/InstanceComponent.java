package Everest.Framework.InversionOfControl.DI.Abstractions;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;

import javax.annotation.Nonnull;

/**
 * The component based on a instance object.
 *
 * @author Chendjou
 * @version 1
 * @since 29-04-2019
 */
public class InstanceComponent extends Component{

    /**
     * The instance object of the component.
     */
    private Object instance;

    public InstanceComponent(@Nonnull ComponentDescriptor descriptor){
        super(descriptor);
        this.instance = descriptor.getImplementationInstance();
        this.implementationType = instance.getClass();
        this.lifetime = ComponentLifetime.SINGLETON;
    }

    public Object getInstance() {
        return instance;
    }

    public InstanceComponent setInstance(Object instance) {
        this.instance = instance;
        return this;
    }
}
