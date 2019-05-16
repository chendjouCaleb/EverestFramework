package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.Inject.Singleton;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The component that take an action method or controller class, parse its annotation and detects filter annotation,
 * which is annotated by {@link FilterBy} an collects them.
 *
 * @author Chendjou
 * @version 1
 * @since 15-05-2019
 */
@Singleton
public class AnnotationFilterGetter {

    public List<Annotation> getFilterAnnotations(Method method) {
        return getFilterDescriptors(method.getAnnotations());
    }

    public List<Annotation> getFilterAnnotations(Class<?> type) {
        return getFilterDescriptors(type.getAnnotations());
    }

    private List<Annotation> getFilterDescriptors(Annotation[] annotations) {
        List<Annotation> collection = new ArrayList<>();

        for(Annotation annotation: annotations){
            if(annotation.annotationType().isAnnotationPresent(FilterBy.class)){
                collection.add(annotation);
            }
        }
        return collection;
    }

}
