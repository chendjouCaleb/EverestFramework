package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;

import java.lang.reflect.Field;

/**
 * Resolves the injection of a class field.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class FieldLookup {
    private LookupEngine lookupEngine;

    public FieldLookup(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
    }

    public void resolveField(Object object, Field field){
        field.setAccessible(true);
        try {
            field.set(object, resolve(field));
        } catch (IllegalAccessException e) {
            throw new ResolutionException(e);
        }
    }

    public Object resolve(Field field){
        UseNamed named = field.getAnnotation(UseNamed.class);
        if (named != null) {
            return lookupEngine.look(named.value());
        } else {
            return lookupEngine.look(field.getType());
        }
    }
}
