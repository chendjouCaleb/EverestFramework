package Everest.Framework.Mvc.Cors;

import Everest.Framework.Http.HeaderNames;
import Everest.Framework.Http.HttpMethod;

/**
 * CORS-related constants.
 * @author Chendjou
 * @version 1
 * @since 25-08-2019
 */
public class CorsConstants {
    /**
     * The HTTP method for the CORS preflight request.
     */
    public static final String PreflightHttpMethod = HttpMethod.OPTIONS.name();

    /**
     * The Origin request header.
     */
    public static final String Origin = HeaderNames.Origin;

    /**
     * The value for the Access-Control-Allow-Origin response header to allow all origins.
     */
    public static final String AnyOrigin = "*";

    /**
     * The Access-Control-Request-Method request header.
     */
    public static final String AccessControlRequestMethod = HeaderNames.AccessControlRequestMethod;

    /**
     * The Access-Control-Request-Headers request header.
     */
    public static final String AccessControlRequestHeaders = HeaderNames.AccessControlRequestHeaders;

    /**
     * The Access-Control-Allow-Origin response header.
     */
    public static final String AccessControlAllowOrigin = HeaderNames.AccessControlAllowOrigin;

    /**
     * The Access-Control-Allow-Headers response header.
     */
    public static final String AccessControlAllowHeaders = HeaderNames.AccessControlAllowHeaders;

    /**
     * The Access-Control-Expose-Headers response header.
     */
    public static final String AccessControlExposeHeaders = HeaderNames.AccessControlExposeHeaders;

    /**
     * The Access-Control-Allow-Methods response header.
     */
    public static final String AccessControlAllowMethods = HeaderNames.AccessControlAllowMethods;

    /**
     * The Access-Control-Allow-Credentials response header.
     */
    public static final String AccessControlAllowCredentials = HeaderNames.AccessControlAllowCredentials;

    /**
     * The Access-Control-Max-Age response header.
     */
    public static final String AccessControlMaxAge = HeaderNames.AccessControlMaxAge;
}
