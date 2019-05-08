package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.StringUtils;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import java.util.NoSuchElementException;


/**
 * Lookup to find a component by name.
 *
 * @author Chendjou
 * @version 1
 * @since 01-05-2019
 */
public class NamedLookup {
    private LookupEngine lookupEngine;
    private ComponentCollection componentCollection;

    public NamedLookup(LookupEngine lookupEngine, ComponentCollection componentCollection) {
        this.lookupEngine = lookupEngine;
        this.componentCollection = componentCollection;
    }

    public Object look(String name){
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Cannot resolveField component with null or empty name");
        }

        if(componentCollection.findByName(name) == null){
            throw new NoSuchElementException(String.format("There are no component with name '%s'", name));
        }
        return lookupEngine.look(name);
    }
}
