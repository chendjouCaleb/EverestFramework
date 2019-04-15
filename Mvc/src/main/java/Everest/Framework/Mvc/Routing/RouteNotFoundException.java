package Everest.Framework.Mvc.Routing;

/**
 * Exception to thrown when a route not found.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException() {
    }

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteNotFoundException(Throwable cause) {
        super(cause);
    }

    public RouteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
