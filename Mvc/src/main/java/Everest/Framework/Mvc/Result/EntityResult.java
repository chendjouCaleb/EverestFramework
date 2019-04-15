package Everest.Framework.Mvc.Result;

import Everest.Framework.Http.StatusCode;


/**
 * An {@link Everest.Framework.Mvc.Result.IActionResult } that when executed will produce a response
 * with an object setBody an {@link Everest.Framework.Http.StatusCode"}.
 * @param <T> The type of the response setBody
 */
public class EntityResult<T> implements IActionResult{

    /**
     * Create an {@link EntityResult} with an setBody
     * @param body The setBody of the response
     */
    public EntityResult(T body){
        this.body = body;
    }
    /**
     * The content representing the setBody of the response.
     */
    private T body;

    /**
     * The Content-Type header for the response.
     */
    private String contentType;


    /**
     * The HTTP status code.
     */
    private int statusCode = 200;

    /**
     * Set the HTTP status code of the response.
     * @param statusCode the HTTP status code
     */
    public EntityResult<T> setStatusCode(int statusCode){
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Set the HTTP status code of the response.
     * @param statusCode the {@link StatusCode } HTTP status code
     */
    public EntityResult<T> setStatusCode(StatusCode statusCode){
        this.statusCode = statusCode.value();
        return this;
    }

    /**
     * Set the setBody representing the setBody of the response.
     * @param body The setBody of the response.
     */
    public EntityResult<T> setBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * Set the Content-Type header for the response.
     * @param contentType The Content-Type header for the response.
     */
    public EntityResult<T> setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public T getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
