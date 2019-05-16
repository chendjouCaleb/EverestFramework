package Everest.Framework.Mvc.Middleware;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Http.HttpContext;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Singleton
public class MiddlewarePipeline {
    private List<IMiddleware> middlewares = new ArrayList<>();

    public void run(HttpContext httpContext){
        MiddlewareChain chain = new MiddlewareChain();
        chain.addMiddlewares(middlewares);
        chain.executeNext(httpContext);
    }

    public void setMiddlewares(List<IMiddleware> middlewares) {
        this.middlewares = middlewares;
    }

    /**
     * Add a new {@link IMiddleware} in the chain.
     * @param middleware The {@link IMiddleware} to add.
     */
    public void addMiddleware(@Nonnull IMiddleware middleware){
        this.middlewares.add(middleware);
    }

    public void addMiddlewares(@Nonnull Collection<IMiddleware> middlewares){
        middlewares.forEach(this::addMiddleware);
    }

    public List<IMiddleware> getMiddlewares() {
        return middlewares;
    }
}
