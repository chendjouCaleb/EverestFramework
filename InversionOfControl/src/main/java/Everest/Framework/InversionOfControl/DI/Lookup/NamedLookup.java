package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.StringUtils;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import javax.annotation.Nonnull;
import java.util.NoSuchElementException;

import static Everest.Framework.InversionOfControl.Message.NAMED_COMPONENT_NOT_FOUND;
import static Everest.Framework.InversionOfControl.Message.NAMED_WITH_EMPTY_NAME;


/**
 * A Lookup to find a component by name.
 *
 * @author Chendjou
 * @version 1
 * @since 01-05-2019
 */
public class NamedLookup {
    private LookupEngine lookupEngine;
    private ComponentCollection componentCollection;

    NamedLookup(@Nonnull LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
        this.componentCollection = lookupEngine.getComponents();
    }

    /**
     * Finds a instance of component that has the specified name.
     * @param name The name of the desired component.
     * @return An instance of component that has the specified name.
     * @throws NoSuchElementException If there are no component that has the specified name.
     */
    public Object look(String name){
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException(NAMED_WITH_EMPTY_NAME);
        }

        if(componentCollection.findByName(name) == null){
            throw new NoSuchElementException(String.format(NAMED_COMPONENT_NOT_FOUND, name));
        }
        Component component = componentCollection.findByName(name);
        return lookupEngine.look(component);
    }
}
