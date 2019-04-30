package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Use it to get all injection field of a component implementation type.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class InjectionFieldGetter {
    /**
     * Get all injection fields of a component implementation type.
     *
     * An injection field is decorated by {@link Resolve}.
     *
     * @param type The implementation type of an component.
     * @return An {@code ArrayList} containing all injection fields of the type.
     */
    public List<Field> getInjectionField(@Nonnull Class type){
        List<Field> fields = new ArrayList<>();
        for(Field field: FieldUtils.getAllFields(type)){
            if(field.isAnnotationPresent(Resolve.class)){
                fields.add(field);
            }
        }
        return fields;
    }
}
