package Everest.Framework.Mvc.Result;

import Everest.Framework.Http.StatusCode;

/**
 * An {@link StatusCodeResult } that when executed will produce an empty
 * {@link Everest.Framework.Http.StatusCode#OK"} response.
 */
public class OkResult extends StatusCodeResult{
    private static final int defaultStatusCode = StatusCode.OK.value();

    public OkResult(){
        super(defaultStatusCode);
    }
}
