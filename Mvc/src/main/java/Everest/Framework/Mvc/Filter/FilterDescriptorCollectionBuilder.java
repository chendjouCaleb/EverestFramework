package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Core.Inject.Singleton;

import java.lang.reflect.Method;

/**
 * Collects all filters of an {@link IActionFilter} class.
 *
 * @author Chendjou
 * @version 1
 * @since 12-05-2019
 */
@Singleton
public class FilterDescriptorCollectionBuilder {
    private OnExecutedFilterDescriptorBuilder onExecutedFilterDescriptorBuilder;
    private OnExecutingFilterDescriptorBuilder onExecutingFilterDescriptorBuilder;

    public FilterDescriptorCollectionBuilder() {
        onExecutedFilterDescriptorBuilder = new OnExecutedFilterDescriptorBuilder();
        onExecutingFilterDescriptorBuilder = new OnExecutingFilterDescriptorBuilder();
    }

    /**
     * Collects all filters of a filter class.
     * @param type The class of the filter.
     * @return A collection all descriptor of filter found in class.
     */
    public FilterDescriptorCollection build(Class<? extends IActionFilter> type) {
        if(type == null){
            throw new NullArgumentException("type");
        }

        FilterDescriptorCollection collection = new FilterDescriptorCollection();

        for(Method method: type.getDeclaredMethods()) {
            if(onExecutingFilterDescriptorBuilder.canBuild(method)){
                collection.add(onExecutingFilterDescriptorBuilder.build(method));
            }

            if(onExecutedFilterDescriptorBuilder.canBuild(method)){
                collection.add(onExecutedFilterDescriptorBuilder.build(method));
            }
        }
        collection.forEach(f -> f.setType(type));

        return collection;
    }


}
