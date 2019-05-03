package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

public interface IComponentResolver<T extends Component> {
    public Object resolve(T component);
}
