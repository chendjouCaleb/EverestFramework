package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static Everest.Framework.InversionOfControl.Message.GET_COLLECTION_OF_NON_TYPED_COLLECTION;

/**
 * The lookup to look a dependencies of a class field.
 *
 * @author Chendjou
 * @version 1
 * @since 08-05-2019
 */
public class FieldLookup {
    private LookupEngine lookupEngine;
    private CollectionLookup collectionLookup;

    public FieldLookup(LookupEngine lookupEngine, CollectionLookup collectionLookup) {
        this.lookupEngine = lookupEngine;
        this.collectionLookup = collectionLookup;
    }

    public Object look(@Nonnull Field field){
        UseNamed named = field.getAnnotation(UseNamed.class);
        if (named != null) {
            return lookupEngine.look(named.value());
        } else if(CollectionLookup.isCollectionType(field.getType())){
            if(!ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())){
                throw new IllegalStateException(GET_COLLECTION_OF_NON_TYPED_COLLECTION);
            }
            Type[] types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();

            return collectionLookup.look(field.getType(), (Class) types[0]);
        }
        else {
            return lookupEngine.look(field.getType());
        }
    }
}
