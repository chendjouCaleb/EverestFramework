package Everest.Framework.Mvc.Filter;

import java.lang.annotation.Annotation;

/**
 * A filter that surrounds execution of the action.
 *
 * @param <T> The type of annotation that decorates the target method.
 *
 * @author Chendjou
 * @version 1
 * @since 15-05-2019
 */
public interface IActionFilter<T extends Annotation> extends IFilterContract {
    /**
     * Is used to pass the information of a annotation of the filter instance.
     * @param annotation The type of annotation that decorates the target method.
     */
    void init(T annotation);
}
