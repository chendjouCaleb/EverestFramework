package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;

import java.lang.reflect.Parameter;

/**
 * Used to resolveField injection for method or constructor resolution.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class ParameterResolver {
    private LookupEngine lookupEngine;
    private NamedLookup namedLookup;

    public ParameterResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.lookupEngine = lookupEngine;
        this.namedLookup = namedLookup;
    }

    public Object resolve(Parameter parameter){
        UseNamed named = parameter.getAnnotation(UseNamed.class);
        if (named != null) {
            return namedLookup.look(named.value());
        } else {
            return lookupEngine.look(parameter.getType());
        }
    }
}
