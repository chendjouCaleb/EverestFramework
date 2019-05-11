package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Used to resolveField injection for method or constructor resolution.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class ParameterLookup {
    private LookupEngine lookupEngine;
    private CollectionLookup collectionLookup;

    public ParameterLookup(LookupEngine lookupEngine) {
        collectionLookup = new CollectionLookup(lookupEngine);
        this.lookupEngine = lookupEngine;
    }

    public Object look(Parameter parameter){
        UseNamed named = parameter.getAnnotation(UseNamed.class);
        if (named != null) {
            return lookupEngine.look(named.value());
        } else if(CollectionLookup.isCollectionType(parameter.getType())){
            if(!ParameterizedType.class.isAssignableFrom(parameter.getParameterizedType().getClass())){
                throw new IllegalStateException("If you want to use collection type for injection, you must use a generics typed collection");
            }
            Type[] types = ((ParameterizedType)parameter.getParameterizedType()).getActualTypeArguments();

            return collectionLookup.look(parameter.getType(), (Class) types[0]);
        }

        else {
            return lookupEngine.look(parameter.getType());
        }
    }
}
