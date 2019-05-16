package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.Result.IActionResult;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A context for action filters, specifically {@link OnActionExecuting} calls.
 *
 * @author Chendjou
 * @version 1
 * @since 15-05-2019
 */
public class OnActionExecutingContext extends FilterContext {

    public OnActionExecutingContext() { }

    public OnActionExecutingContext(@Nonnull ActionContext context, List<IFilterContract> filters, Object controller) {
        super(context, filters);

        if(controller ==null){
            throw new NullArgumentException("controller");
        }

        this.controller = controller;
    }

    /**
     * Gets or sets the result to execute. Setting result to a non-{@code null}
     * value inside an action filter will short-circuit the action and any remaining action filters.
     */
    private IActionResult result;

    /**
     * Gets the controller instance containing the action.
     */
    private Object controller;

    public IActionResult getResult() {
        return result;
    }

    public void setResult(IActionResult result) {
        this.result = result;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {

        this.controller = controller;
    }
}
