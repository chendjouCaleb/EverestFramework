package Everest.Framework.Mvc.Runner;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Middleware.IMiddleware;
import Everest.Framework.Mvc.Middleware.MiddlewareChain;



public class MvcMiddleware implements IMiddleware {
//    private MvcRunner mvcRunner;
//
//    public MvcMiddleware(MvcRunner mvcRunner) {
//        this.mvcRunner = mvcRunner;
//    }

    @Override
    public void execute(MiddlewareChain chain, HttpContext httpContext) {

       // mvcRunner.run(httpContext);
    }
}
