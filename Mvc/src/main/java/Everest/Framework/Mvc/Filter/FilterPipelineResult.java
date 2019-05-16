package Everest.Framework.Mvc.Filter;

import Everest.Framework.Mvc.Result.IActionResult;

/**
 * Represents the result of the execution of an before action filter pipeline.
 */
public class FilterPipelineResult {

    public FilterPipelineResult() {
    }

    public FilterPipelineResult(IActionResult result) {
        this.result = result;
    }

    /**
     * The result of short-circuit filter.
     */
    private IActionResult result;

    /**
     * The last executed filter.
     */
    private IFilterContract lastFilter;

    /**
     * Tell that if all filters of the pipeline have been executed.
     */
    public boolean isFinished() {
        return result == null;
    }


    public IActionResult getResult() {
        return result;
    }

    public void setResult(IActionResult result) {
        this.result = result;
    }

    public IFilterContract getLastFilter() {
        return lastFilter;
    }

    public void setLastFilter(IFilterContract lastFilter) {
        this.lastFilter = lastFilter;
    }
}
