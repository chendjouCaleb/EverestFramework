package Everest.Framework.InversionOfControl.DI.Lookup;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

class CollectionLookupTest {
    List<Double> list = new ArrayList<>();
    @Test
    void look() throws NoSuchMethodException, NoSuchFieldException {
        Method method = getClass().getMethod("x", List.class);
        Parameter parameter = method.getParameters()[0];

        Field e = getClass().getDeclaredField("list");

        System.out.println(((ParameterizedType)e.getGenericType()).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedType)parameter.getParameterizedType()).getActualTypeArguments()[0]);
    }

    public void x(List<Integer> c){

    }
}