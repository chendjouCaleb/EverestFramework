package Everest.Framework.Tomcat;

import Everest.Framework.Http.DefaultHttpContext;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Mvc.Middleware.MiddlewarePipeline;
import Everest.Framework.Mvc.Runner.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(AppServlet.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpContext httpContext = new DefaultHttpContext();
        httpContext.setRequest(new ServletRequestImpl(request));
        httpContext.setResponse(new ServletResponseImpl(response));

        WebApplication application =
                (WebApplication) getServletContext().getAttribute("applicationContext");

        logger.debug("New request: [ url = {}, method = {}]", httpContext.getRequest().path(), httpContext.getRequest().method());
        MiddlewarePipeline pipeline = application.getComponentProvider().getComponent(MiddlewarePipeline.class);
        pipeline.run(httpContext);
    }
}
