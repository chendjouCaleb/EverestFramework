package Everest.Framework.Tomcat;

import Everest.Framework.Mvc.MvcStartup;
import Everest.Framework.Mvc.Runner.HostConfig;
import Everest.Framework.Mvc.Runner.WebApplication;
import Everest.Framework.Mvc.Runner.WebServer;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TomcatServer extends WebServer {
    private Logger logger = LoggerFactory.getLogger(TomcatServer.class);
    private boolean hasStarted = false;
    private Tomcat tomcat;

    private MvcStartup mvcStartup;

    public TomcatServer(MvcStartup mvcStartup) {
        this.mvcStartup = mvcStartup;
    }

    public void build(WebApplication application, HostConfig config) {
        long begin = System.currentTimeMillis();

        System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "*.jar");

        tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(config.getPort());
        tomcat.getHost().setAppBase(config.getAppBasePath());


        Context context = tomcat.addContext(config.getHostPath(), new File(config.getContextDirectory()).getAbsolutePath());
        File classDir = new File(config.getTargetClassDirectory());
        WebResourceRoot resourceRoot = new StandardRoot(context);
        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, config.getWebAppMount(),
                classDir.getAbsolutePath(), config.getInternalPath() ));

        context.addApplicationListener("Everest.Framework.Tomcat.ApplicationStartupListener");
        context.getServletContext().setAttribute("mvcStartup", application.getMvcStartup());
        context.getServletContext().setAttribute("applicationContext", application);




        long duration = System.currentTimeMillis()-begin;
        logger.info("Server: [Duration={} s, PORT = {}]", (double)duration/1000, tomcat.getServer().getPort());
    }

    @Override
    public void listen() {
        try {
            tomcat.start();
            this.hasStarted = true;
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
        tomcat.getServer().await();
    }

    @Override
    public void stop() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasStarted() {
        return hasStarted;
    }

    private String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." );
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
}

