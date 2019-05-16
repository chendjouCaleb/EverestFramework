package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Mvc.Action.ActionContext;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * An abstract context for filters.
 *
 * @author Chendjou
 * @version 1
 * @since 15-05-2019
 */
public abstract class FilterContext extends ActionContext {
    /**
     * Gets all applicable {@link IFilterContract} implementations.
     */
    private List<IFilterContract> filters;

    public FilterContext() {}

    public FilterContext(@Nonnull ActionContext context, List<IFilterContract> filters){
        super(context);
        if(filters == null){
            throw new NullArgumentException("filters");
        }

        this.filters = filters;
    }

    public List<IFilterContract> getFilters() {
        return filters;
    }

    public void setFilters(List<IFilterContract> filters) {
        this.filters = filters;
    }
}
