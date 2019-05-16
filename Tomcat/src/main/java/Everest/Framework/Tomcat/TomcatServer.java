package Everest.Framework.Tomcat;

import Everest.Framework.Mvc.MvcStartup;
import Everest.Framework.Mvc.Runner.WebApplicationContext;
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

    public void build(WebApplicationContext applicationContext) {
        long begin = System.currentTimeMillis();

        System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "*.jar");
        String appBase = ".";
        tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(appBase);


        Context context = tomcat.addContext(hostPath, new File(contextDirectory).getAbsolutePath());
        File classDir = new File(targetClassDirectory);
        WebResourceRoot resourceRoot = new StandardRoot(context);
        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/WEB-INF/classes", classDir.getAbsolutePath(), "/" ));

        context.addApplicationListener("Everest.Framework.Tomcat.ApplicationStartupListener");
        context.getServletContext().setAttribute("mvcStartup", applicationContext.getMvcStartup());
        context.getServletContext().setAttribute("applicationContext", applicationContext);


        try {
            tomcat.start();
            this.hasStarted = true;
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }

        long duration = System.currentTimeMillis()-begin;
        logger.info("Server: [Duration={} s, PORT = {}]", (double)duration/1000, tomcat.getServer().getPort());
    }

    @Override
    public void listen() {
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
            File tempDir = File.createTempFile("tomcat.", "." + getPort());
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

