package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import java.util.List;
import java.util.NoSuchElementException;

import static Everest.Framework.InversionOfControl.Message.TYPED_COMPONENT_NOT_FOUND;

/**
 * A lookup to find a instance of a component by his type
 *
 * @author Chendjou
 * @version 1
 * @since 09-05-2019
 */
public class TypeLookup {
    private LookupEngine lookupEngine;
    private ComponentCollection componentCollection;
    private PrincipalLookup principalLookup;

    public TypeLookup(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
        componentCollection = lookupEngine.getComponents();
        principalLookup = new PrincipalLookup(lookupEngine);
    }

    /**
     * Finds the instance value of component of the specified type.
     * @param componentType The type of component to find.
     * @return The instance of the specified type.
     *
     * @throws NoSuchElementException If there are no registered component with specified type.
     */
    public Object look(Class<?> componentType) {
        List<Component> components = componentCollection.listByComponentTypes(componentType);

        if(components.size() == 0){
            throw new NoSuchElementException(String.format(TYPED_COMPONENT_NOT_FOUND, componentType.getName()));
        }
        else if(components.size() == 1){
            return lookupEngine.look(components.get(0));
        }
        return principalLookup.look(componentType);

    }
}
