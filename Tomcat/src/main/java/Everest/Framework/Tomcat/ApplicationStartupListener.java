package Everest.Framework.Tomcat;

import Everest.Framework.Mvc.MvcStartup;
import Everest.Framework.Mvc.Runner.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationStartupListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MvcStartup clazz = (MvcStartup) sce.getServletContext().getAttribute("mvcStartup");

        logger.info("Application started. Startup class: " + clazz.getClass().getName());

        WebApplication application = (WebApplication) sce.getServletContext().getAttribute("applicationContext");
        addApplicationServlet(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
System.out.println("App was been stopped");
    }



    private void addApplicationServlet(ServletContextEvent sce) {
        ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet("AppServlet",new AppServlet());
        dynamic.addMapping("/*");
        //dynamic.setMultipartConfig(multipartConfigElement(initializer.multiPartConfig()));
    }
}
