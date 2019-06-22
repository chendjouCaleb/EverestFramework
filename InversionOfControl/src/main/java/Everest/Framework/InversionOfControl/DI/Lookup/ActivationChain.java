package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

import java.util.ArrayList;

/**
 * Used to store all the related activated component of an one activation.
 * Is useful to detect circular dependencies. If a component is add twice, there
 * is a circular dependencies.
 *
 * @author Chendjou
 * @version 1
 * @since 22-06-2016
 */
public class ActivationChain extends ArrayList<Component> {
    @Override
    public boolean add(Component component) {
        if (component == null) {
            throw new NullArgumentException("Cannot add null component in activation chain.");
        }
        int index = indexOf(component);
        if (index > -1) {
            String message = String.format("Circular dependencies detected during the activation of " +
                            "type %s with implementation type %s. \nActivation stack:", component.getComponentType(),
                    component.getImplementationType());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(message);

            for (int i = index; i < size(); i++) {
                stringBuilder
                        .append(get(i).instanceProviderToString())
                        .append(" -> ");
            }
            stringBuilder.append(component.instanceProviderToString());
            throw new InvalidOperationException(stringBuilder.toString());
        }
        return super.add(component);
    }
}
