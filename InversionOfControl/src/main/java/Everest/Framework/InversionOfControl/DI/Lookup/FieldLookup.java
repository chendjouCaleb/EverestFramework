package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.UseNamed;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Resolves the injection of a class field.
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

    public void resolveField(Object object, Field field){
        field.setAccessible(true);
        try {
            field.set(object, resolve(field));
        } catch (IllegalAccessException e) {
            throw new ResolutionException(e);
        }
    }

    public Object resolve(Field field){
        UseNamed named = field.getAnnotation(UseNamed.class);
        if (named != null) {
            return lookupEngine.look(named.value());
        } else if(CollectionLookup.isCollectionType(field.getType())){
            if(!ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())){
                throw new IllegalStateException("If you want to use collection type for injection, you must use a generics typed collection");
            }
            Type[] types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();

            return collectionLookup.look(field.getType(), (Class) types[0]);
        }
        else {
            return lookupEngine.look(field.getType());
        }
    }
}
