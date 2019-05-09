package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;

import java.lang.reflect.Parameter;

/**
 * Used to resolveField injection for method or constructor resolution.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class ParameterLookup {
    private LookupEngine lookupEngine;

    public ParameterLookup(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
    }

    public Object resolve(Parameter parameter){
        UseNamed named = parameter.getAnnotation(UseNamed.class);
        if (named != null) {
            return lookupEngine.look(named.value());
        } else {
            return lookupEngine.look(parameter.getType());
        }
    }
}
