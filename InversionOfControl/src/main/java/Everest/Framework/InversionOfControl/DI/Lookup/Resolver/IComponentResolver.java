package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

public interface IComponentResolver<T extends Component> {
    Object resolve(T component);
}
