package Everest.Framework.InversionOfControl.DI.Lookup.Resolver;

import Everest.Framework.Core.Reflexions;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Lookup.LookupEngine;
import Everest.Framework.InversionOfControl.DI.Lookup.NamedLookup;
import Everest.Framework.InversionOfControl.DI.Lookup.ResolutionException;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

public class FactoryMethodResolver implements IComponentResolver<FactoryMethodComponent> {
    private LookupEngine lookupEngine;
    private ParametersResolver parametersResolver;


    public FactoryMethodResolver(LookupEngine lookupEngine, NamedLookup namedLookup) {
        this.lookupEngine = lookupEngine;
        parametersResolver = new ParametersResolver(new ParameterResolver(lookupEngine, namedLookup));
    }

    @Override
    public Object resolve(@Nonnull FactoryMethodComponent component) {
        Method method = component.getFactoryMethod();
        try {
            Object parentInstance = lookupEngine.look(component.getTypeComponent());

            Object[] values = parametersResolver.resolve(method.getParameters());

            return Reflexions.callRemote(parentInstance, method, values);
        } catch (Exception e) {
            throw new ResolutionException(String.format("Error during resolution of factory method '%s'", method.getName()), e);

        }

    }
}
