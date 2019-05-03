package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

import java.util.HashMap;
import java.util.Objects;

/**
 * Contains all object associated to the singleton components of the application.
 *
 * @author Chendjou
 * @version 1
 * @since 02-05-2019
 */
public class RootComponentCache extends HashMap<Component, Object> {
    @Override
    public Object put(Component key, Object value) {
        if(key.getLifetime() != ComponentLifetime.SINGLETON){
            throw new IllegalStateException("Cannot cache the instance of a non singleton component");
        }

        Objects.requireNonNull(value, "Cannot cache null value");

        return super.put(key, value);
    }
}
