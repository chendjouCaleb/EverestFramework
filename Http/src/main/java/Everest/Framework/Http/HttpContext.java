package Everest.Framework.Http;


/**
 * Encapsulates all HTTP-specific information about an individual HTTP request.
 */
public abstract class HttpContext {
    private Object controller;

    /**
     * Gets the {@link HttpRequest }object for this request.
     */
    protected HttpRequest request;

    /**
     * Gets the {@link HttpResponse } object for this request.
     */
    protected HttpResponse response;

    /**
     * Gets information about the underlying connection for this request.
     */
    private ConnectionInfo connectionInfo;

    /**
     * Gets or sets a key/value collection that can be used to share data within the scope of this request.
     */
    private ItemCollection items = new ItemCollection();

    /**
     * Additional options for the HttpContext
     */
    private HttpContextOptions options = new HttpContextOptions();


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }


    public void setController(Object controller) {
        this.controller = controller;
    }




    public HttpContextOptions getOptions() {
        return options;
    }

    public void setOptions(HttpContextOptions options) {
        this.options = options;
    }

    public ConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    public ItemCollection getItems() {
        return items;
    }
}
