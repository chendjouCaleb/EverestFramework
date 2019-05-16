package Everest.Framework.Mvc.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDescriptorCollection extends ArrayList<FilterDescriptor> {
    public FilterDescriptorCollection getBeforeActionFilters() {
        FilterDescriptorCollection collection = new FilterDescriptorCollection();
        List<FilterDescriptor> filters = stream().filter(d -> d.getFilterType().equals(FilterType.OnActionExecuting))
                .collect(Collectors.toList());

        collection.addAll(filters);
        return collection;
    }
}
