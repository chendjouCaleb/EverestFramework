package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

import java.util.HashMap;
import java.util.Objects;

public class ScopeComponentCache extends HashMap<Component, Object> {
    @Override
    public Object put(Component key, Object value) {
        if(key.getLifetime() != ComponentLifetime.SCOPED){
            throw new IllegalStateException("Cannot cache the instance of a non scoped component");
        }

        Objects.requireNonNull(value, "Cannot cache null value");

        return super.put(key, value);
    }
}
