package Everest.Framework.Mvc.Runner;


public abstract class WebServer {



    /**
     * To create the web server
     */
    public abstract void build(WebApplication application, HostConfig config);

    /**
     * Do that the server can receive a http request
     */
    public abstract void listen();


    /**
     * Stop the server. The server cannot receive http request
     */
    public abstract void stop();

    /**
     * Check if server has started
     * @return True if server has started
     */
    public abstract boolean hasStarted();
}
