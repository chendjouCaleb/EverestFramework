package Everest.Framework.InversionOfControl.DI.Lookup;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Resolves all parameters injector of a constructor or a method.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class ParametersLookup {
    private ParameterLookup parameterLookup;

    public ParametersLookup(ParameterLookup parameterLookup) {
        this.parameterLookup = parameterLookup;
    }

    public Object[] resolve(Parameter[] parameters){
        List<Object> values = new ArrayList<>(parameters.length);

        for (Parameter parameter : parameters) {
            values.add(parameterLookup.resolve(parameter));
        }

        return values.toArray();
    }
}
