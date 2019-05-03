package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.InstanceComponent;



public class InstanceResolver implements IComponentResolver<InstanceComponent> {

    public Object resolve(InstanceComponent component) {
        return component.getInstance();
    }
}
