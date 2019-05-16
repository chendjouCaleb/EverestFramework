package Everest.Framework.Mvc.Filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Provides a description on an action filter.
 *
 * @author Chendjou
 * @version 1
 * @since 12-05-2019
 */
public class FilterDescriptor {
    /**
     * The filter class.
     */
    private Class<? extends IActionFilter> type;
    /**
     * The method of the filter.
     */
    private Method method;


    /**
     * The annotation which decorated the target action method.
     */
    private Annotation annotation;

    /**
     * The order ito execute the filter.
     */
    private int order = 0;

    private FilterType filterType;

    public FilterDescriptor() {}

    public FilterDescriptor(Class<? extends IActionFilter> type, Method method, Annotation annotation, FilterType filterType) {
        this.type = type;
        this.method = method;
        this.annotation = annotation;
        this.filterType = filterType;
    }

    public FilterDescriptor(Class<? extends IActionFilter> type, Method method, FilterType filterType, int order) {
        this.method = method;
        this.order = order;
        this.filterType = filterType;
        this.type = type;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public Class<? extends IActionFilter> getType() {
        return type;
    }

    public void setType(Class<? extends IActionFilter> type) {
        this.type = type;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "FilterDescriptor{" +
                "type=" + type.getSimpleName() +
                ", method=" + method.getName() +
                ", annotation=" + annotation.annotationType().getSimpleName() +
                ", order=" + order +
                ", filterType=" + filterType +
                '}';
    }
}
