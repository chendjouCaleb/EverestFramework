package Everest.Framework.InversionOfControl.Utils;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class InvokerUtils {
    public static Object invokeConstructor(Constructor constructor, Object... params){
        try {
            return constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invokeConstructor(Class<?> type, Object... params){
        try {
            return ConstructorUtils.invokeConstructor(type, params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
