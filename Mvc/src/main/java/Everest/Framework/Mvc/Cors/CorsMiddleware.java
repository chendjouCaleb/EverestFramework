package Everest.Framework.Mvc.Cors;

import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.HttpResponse;
import Everest.Framework.Mvc.Middleware.IMiddleware;
import Everest.Framework.Mvc.Middleware.MiddlewareChain;
import Everest.Framework.Mvc.MvcStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorsMiddleware implements IMiddleware {
    private MvcStartup mvcStartup;
    private Logger logger = LoggerFactory.getLogger(CorsMiddleware.class);

    public CorsMiddleware(MvcStartup mvcStartup) {
        this.mvcStartup = mvcStartup;
    }

    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        httpContext.getResponse().setStatusCode(200);
        configureCors(httpContext.getResponse());
        if (!httpContext.getRequest().method().equals("OPTIONS")) {
            chain.executeNext(httpContext);
        }
    }

    private void configureCors(HttpResponse response){
        CORSConfig config = mvcStartup.corsConfig();
        response.addHeader("Access-Control-Allow-Credentials", config.getAllowCredentials().toString());
        response.addHeader("Access-Control-Allow-Methods", config.getAllowedMethods());
        response.addHeader("Access-Control-Allow-Headers", config.getAllowedHeaders());
        response.addHeader("Access-Control-Allow-Origin", config.getAllowedOrigin());
        logger.info("CORS Config = {}", config);
    }
}
