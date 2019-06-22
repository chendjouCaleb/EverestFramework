package Everest.Framework.Mvc.Runner;

import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.StringUtils;
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
import java.util.function.Consumer;

public class WebApplication {
    private Logger logger = LoggerFactory.getLogger(WebApplication.class);
    private PackageComponentProviderBuilder componentRegister;
    private IComponentProvider componentProvider;
    private MvcStartup mvcStartup;
    private Class<? extends WebServer> webServerClass;
    private WebServer webServer;
    private HostConfig hostConfig;
    private List<String> basePackages = new ArrayList<>();


    public WebApplication(){
        componentRegister = new PackageComponentProviderBuilder();
    }

    public void build() {
        if(mvcStartup == null){
            throw new NullArgumentException("Cannot start without a MvcStartup class");
        }

        if(webServerClass == null) {
            throw new NullArgumentException("Cannot start with a null server class");
        }

        buildComponentProvider();

        configureControllers();

        configureMiddlewares();


        WebServer webServer = componentProvider.getComponent(WebServer.class);
        webServer.build(this, hostConfig);
        webServer.listen();
    }

    private void buildComponentProvider() {
        componentRegister.addTypeFilter(new ControllerTypeFilter());

        componentRegister.addTypeFilter(new MiddlewareTypeFilter());
        componentRegister.addTypeFilter(new ResponseFormatterTypeFilter());
        componentRegister.addTypeFilter(new ActionResultExecutorTypeFilter());
        componentRegister.addTypeFilter(new AnnotationValueResolverTypeFilter());
        componentRegister.addTypeFilter(new TypedValueResolverTypeFilter());
        componentRegister.addTypeFilter(new ActionFilterTypeFilter());

        componentRegister.addSingleton(MvcStartup.class, mvcStartup);
        componentRegister.addSingleton(WebServer.class, webServerClass);

        this.addPackageName("Everest.Framework");
        for (String name: basePackages){
            componentRegister.addPackageName(name);
        }
        componentProvider = this.componentRegister.buildComponentProvider();
    }

    private void configureControllers() {
        List<Class<?>> controllers = new ArrayList<>();

        for(ComponentDescriptor descriptor: componentRegister){
            if(descriptor.getComponentType().isAnnotationPresent(HttpMapping.class)){
                controllers.add(descriptor.getComponentType());
                logger.info("Controller: {}", descriptor.getComponentType().getName());
            }
        }
        ActionConfigurer actionConfigurer = componentProvider.getComponent(ActionConfigurer.class);
        actionConfigurer.collectAction(controllers);
    }

    private void configureMiddlewares() {
        MiddlewarePipelineConfigurer middlewarePipelineConfigurer =
                componentProvider.getComponent(MiddlewarePipelineConfigurer.class);

        List<IMiddleware> middlewares = componentProvider.getComponents(IMiddleware.class);
        MiddlewareRegister middlewareRegister = new MiddlewareRegister();
        mvcStartup.setMiddlewareChain(middlewareRegister);
        middlewarePipelineConfigurer.configure(middlewareRegister, middlewares);
    }

    public WebApplication setMvcStartup(@Nonnull Class<? extends MvcStartup> mvcStartupClass) {
        try {
            mvcStartup = mvcStartupClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }



    public WebApplication useWebServer(Class<? extends WebServer> webServerClass, Consumer<HostConfig> hostConfigConsumer) {
        this.webServerClass = webServerClass;
        this.hostConfig = new HostConfig();
        hostConfigConsumer.accept(hostConfig);
        return this;
    }

    public WebApplication useWebServer(Class<? extends WebServer> webServerClass) {
        this.webServerClass = webServerClass;
        this.hostConfig = new HostConfig();
        return this;
    }

    public WebApplication addPackageName(String packageName) {
        if(StringUtils.isEmpty(packageName)) {
            throw new NullArgumentException("You cannot use null or empty package name");
        }

        if(basePackages.contains(packageName)){
            throw new InvalidOperationException("You can not add the same package name two time");
        }

        basePackages.add(packageName);
        return this;
    }


    public WebServer getWebServer() { return webServer; }

    public IComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public PackageComponentProviderBuilder getComponentRegister() {
        return componentRegister;
    }

    public MvcStartup getMvcStartup() {
        return mvcStartup;
    }

}
