package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.ParameterLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ParametersLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ResolutionException;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

public class FactoryMethodComponentResolver implements IComponentResolver<FactoryMethodComponent> {
    private LookupEngine lookupEngine;
    private ParametersLookup parametersLookup;


    public FactoryMethodComponentResolver(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
        parametersLookup = new ParametersLookup(new ParameterLookup(lookupEngine));
    }

    @Override
    public Object resolve(@Nonnull FactoryMethodComponent component) {
        Method method = component.getFactoryMethod();
        try {
            Object parentInstance = lookupEngine.look(component.getTypeComponent());

            Object[] values = parametersLookup.resolve(method.getParameters());

            return Reflexions.callRemote(parentInstance, method, values);
        } catch (Exception e) {
            throw new ResolutionException(String.format("Error during resolution of factory method '%s'", method.getName()), e);

        }

    }
}
