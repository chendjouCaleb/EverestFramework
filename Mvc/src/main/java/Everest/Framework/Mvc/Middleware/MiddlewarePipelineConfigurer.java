package Everest.Framework.Mvc.Middleware;

import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.MiddlewareRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Singleton
public class MiddlewarePipelineConfigurer {
    private MiddlewarePipeline pipeline;
    private Logger logger = LoggerFactory.getLogger(MiddlewarePipelineConfigurer.class);

    public MiddlewarePipelineConfigurer(MiddlewarePipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void configure(MiddlewareRegister register, Collection<IMiddleware> middlewares){
        register.getMiddlewares().forEach(type -> {
            IMiddleware middleware = middlewares.stream().filter(m -> m.getClass().equals(type))
                    .findFirst().orElseThrow(() ->
                            new IllegalArgumentException("The instance of class " + type + " is not present in middleware collection"));
            pipeline.addMiddleware(middleware);
            logger.info("Registering middleware: [{}]", middleware.getClass().getName());
        });
    }
}
