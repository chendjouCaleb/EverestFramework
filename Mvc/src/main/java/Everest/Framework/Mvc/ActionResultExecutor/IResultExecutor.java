package Everest.Framework.Mvc.ActionResultExecutor;


import Everest.Framework.Http.HttpContext;

import java.io.IOException;

/**
 * The interface of class which can execute the result of the execution of an action method.
 * @param <T> The type of the result.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface IResultExecutor<T> {
    void execute(HttpContext httpContext, T result) throws IOException;
}
