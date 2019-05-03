package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Inject.UseNamed;
import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ResolutionException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class FactoryMethodResolver implements IComponentResolver<FactoryMethodComponent> {
    private LookupEngine lookupEngine;
    private NamedLookup namedLookup;

    public FactoryMethodResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.lookupEngine = lookupEngine;
        this.namedLookup = namedLookup;
    }

    @Override
    public Object resolve(FactoryMethodComponent component) {
        Method method = component.getFactoryMethod();
        try {
            Object parentInstance = lookupEngine.look(component.getTypeComponent());

            Object[] values = new Parameter[method.getParameters().length];
            int i = 0;
            for (Parameter parameter : method.getParameters()) {
                UseNamed named = parameter.getAnnotation(UseNamed.class);
                if (named != null) {
                    values[i] = namedLookup.look(named.value());
                } else {
                    values[i] = lookupEngine.look(parameter.getType());
                }
                i++;
            }

            return Reflexions.callRemote(parentInstance, method, values);
        } catch (Exception e) {
            throw new ResolutionException(String.format("Error during resolution of factory method '%s'", method.getName()), e);

        }

    }
}
