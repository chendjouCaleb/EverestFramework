package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ResolutionException;

import java.lang.reflect.Field;

/**
 * Resolves the injection of a class field.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class FieldResolver {
    private LookupEngine lookupEngine;
    private NamedLookup namedLookup;

    public FieldResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.lookupEngine = lookupEngine;
        this.namedLookup = namedLookup;
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
            return namedLookup.look(named.value());
        } else {
            return lookupEngine.look(field.getType());
        }
    }
}
