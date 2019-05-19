package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

import java.util.List;
import java.util.stream.Collectors;

import static Everest.Framework.InversionOfControl.Message.NO_PRINCIPAL_COMPONENT;

/**
 * A lookup to find the principal component of multiple component with same component type.
 * A principal component have the field isPrincipal as {@code true}.
 * @see Component
 *
 * @author Chendjou
 * @version 1
 * @since 09-05-2019
 */
public class PrincipalLookup {
    private LookupEngine lookupEngine;

    public PrincipalLookup(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
    }

    /**
     * Finds the principal component of multiple component with same component type.
     * @param componentType he type of the component to find.
     * @return The instance of the principal component of the specified component type.
     *
     * @throws NoPrincipalComponentException If the groups of component dont have a principal component.
     * @throws ManyPrincipalComponentException If the component have multiple principal component.
     */
    public Object look(Class componentType) {
        List<Component> principals = lookupEngine.getComponents().listByComponentTypes(componentType)
                .stream().filter(Component::isPrincipal).collect(Collectors.toList());

        //No principal component
        if (principals.size() == 0) {
            throw new NoPrincipalComponentException(String.format(NO_PRINCIPAL_COMPONENT, componentType.getName()));
        }

        //Many principal components
        if (principals.size() > 1) {
            throw new ManyPrincipalComponentException(
                    String.format("There are to many component with type '%s' marked as principal", componentType.getName()));
        } else {
            return lookupEngine.look(principals.get(0));
        }

    }
}
