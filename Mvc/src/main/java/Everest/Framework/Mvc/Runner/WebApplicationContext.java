package Everest.Framework.Mvc.Runner;

import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.Packages.PackageComponentProviderBuilder;
import Everest.Framework.Mvc.ActionResultExecutor.ActionResultExecutorTypeFilter;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Middleware.IMiddleware;
import Everest.Framework.Mvc.Middleware.MiddlewarePipelineConfigurer;
import Everest.Framework.Mvc.Middleware.MiddlewareTypeFilter;
import Everest.Framework.Mvc.MiddlewareRegister;
import Everest.Framework.Mvc.MvcStartup;
import Everest.Framework.Mvc.ResponseFormatter.ResponseFormatterTypeFilter;
import Everest.Framework.Mvc.ValueResolver.AnnotationValueResolverTypeFilter;
import Everest.Framework.Mvc.ValueResolver.TypedValueResolverTypeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WebApplicationContext{
    private Logger logger = LoggerFactory.getLogger(WebApplicationContext.class);
    private PackageComponentProviderBuilder componentRegister;
    private IComponentProvider componentProvider;
    private MvcStartup mvcStartup;
    private Class<? extends WebServer> webServerClass;


    public WebApplicationContext(){
        componentRegister = new PackageComponentProviderBuilder();
        componentRegister.addPackageName("Everest.Framework");
        componentRegister.addTypeFilter(new ControllerTypeFilter());

        componentRegister.addTypeFilter(new MiddlewareTypeFilter());
        componentRegister.addTypeFilter(new ResponseFormatterTypeFilter());
        componentRegister.addTypeFilter(new ActionResultExecutorTypeFilter());
        componentRegister.addTypeFilter(new AnnotationValueResolverTypeFilter());
        componentRegister.addTypeFilter(new TypedValueResolverTypeFilter());
        componentRegister.addTypeFilter(new ActionFilterTypeFilter());
    }

    public void build() {
        if(mvcStartup == null){
            throw new IllegalStateException("Cannot start without a MvcStartup class");
        }
        componentRegister.addSingleton(MvcStartup.class, mvcStartup);
        componentRegister.addSingleton(WebServer.class, webServerClass);
        for (String name: mvcStartup.getBasePackages()){
            componentRegister.addPackageName(name);
        }
        componentProvider = this.componentRegister.buildComponentProvider();

        List<Class<?>> controllers = new ArrayList<>();

        for(ComponentDescriptor descriptor: componentRegister){
            if(descriptor.getComponentType().isAnnotationPresent(HttpMapping.class)){
                controllers.add(descriptor.getComponentType());
                logger.info("Controller: {}", descriptor.getComponentType().getName());
            }
        }
        ActionConfigurer actionConfigurer = componentProvider.getComponent(ActionConfigurer.class);
        actionConfigurer.collectAction(controllers);


        MiddlewarePipelineConfigurer middlewarePipelineConfigurer =
                componentProvider.getComponent(MiddlewarePipelineConfigurer.class);

        List<IMiddleware> middlewares = componentProvider.getComponents(IMiddleware.class);
        MiddlewareRegister middlewareRegister = new MiddlewareRegister();
         mvcStartup.setMiddlewareChain(middlewareRegister);
        middlewarePipelineConfigurer.configure(middlewareRegister, middlewares);

        WebServer webServer = componentProvider.getComponent(WebServer.class);
        webServer.build(this);
        webServer.listen();
    }

    public PackageComponentProviderBuilder getComponentRegister() {
        return componentRegister;
    }

    public MvcStartup getMvcStartup() {
        return mvcStartup;
    }

    public WebApplicationContext setMvcStartup(@Nonnull Class<? extends MvcStartup> mvcStartupClass) {
        try {
            mvcStartup = mvcStartupClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public IComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public WebApplicationContext setWebServerClass(Class<? extends WebServer> webServerClass) {
        this.webServerClass = webServerClass;
        return this;
    }
}
