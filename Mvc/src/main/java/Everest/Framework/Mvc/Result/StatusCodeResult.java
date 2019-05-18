package Everest.Framework.Mvc.Result;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Http.StatusCode;

/**
 * Represents an {@link Everest.Framework.Mvc.Result.IActionResult} that when executed will
 *  produce an HTTP response with the given response status code.
 */
public class StatusCodeResult implements IActionResult {
    private Integer statusCode;

    public static StatusCodeResult OK = new StatusCodeResult(StatusCode.OK);
    public static StatusCodeResult NO_CONTENT = new StatusCodeResult(StatusCode.NO_CONTENT);

    public StatusCodeResult(Integer statusCode) {
        if(statusCode == null){
            throw new NullArgumentException();
        }
        this.statusCode = statusCode;
    }

    public StatusCodeResult(StatusCode statusCode){
        this(statusCode.value());
    }

    /**
     * Gets the HTTP status code.
     * @return The HTTP status code
     */
    public Integer getStatusCode() {
        return statusCode;
    }
}
