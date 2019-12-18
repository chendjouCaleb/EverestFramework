package Everest.Framework.Mvc.Cors;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Defines the policy for Cross-Origin requests based on the CORS specifications.
 *
 * @author Chendjou
 * @since 25-08-2019
 * @version 1
 */
public class CorsPolicy {
    private static final String _wildcardSubdomain = "*.";
    /**
     * Headers that the resource might use and can be exposed
     */
    private List<String> exposedHeaders = new ArrayList<>();

    /**
     * Headers that are supported by the resource.
     */
    private List<String> headers = new ArrayList<>();

    /**
     * Methods that are supported by the resource.
     */
    private List<String> methods = new ArrayList<>();

    /**
     * Origins that are allowed to access the resource.
     */
    private List<String> origins = new ArrayList<>();

    /**
     * A value indicating whether the resource supports user credentials in the request.
     */
    private boolean supportsCredentials;

    /**
     * A function which evaluates whether an origin is allowed.
     */
    private Predicate<String> isOriginAllowed;

    /**
     * Gets value indicating if all headers are allowed.
     * @return a value indicating if all headers are allowed.
     */
    public boolean allowAnyHeader(){
        return headers != null && headers.size() == 1 && headers.get(0).equals("*");
    }

    /**
     * Gets a value indicating if all methods are allowed.
     * @return A value indicating if all methods are allowed.
     */
    public boolean allowAnyMethod(){
        return methods != null && methods.size() == 1 && methods.get(0).equals("*");
    }

    /**
     * Gets a value indicating if all origins are allowed.
     * @return A value indicating if all origins are allowed.
     */
    public boolean allowAnyOrigin(){
        return origins != null && origins.size() == 1 && origins.get(0).equals("*");
    }

    public boolean isOriginAnAllowedSubdomain(String origin)
    {

        return false;
    }

    private boolean defaultIsOriginAllowed(String origin)
    {
        return origins.contains(origin);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AllowAnyHeader: ");
        builder.append(allowAnyHeader());
        builder.append(", AllowAnyMethod: ");
        builder.append(allowAnyMethod());
        builder.append(", AllowAnyOrigin: ");
        builder.append(allowAnyOrigin());
//        builder.append(", PreflightMaxAge: ");
//        builder.append(PreflightMaxAge.HasValue ?
//                PreflightMaxAge.Value.TotalSeconds.ToString() : "null");
        builder.append(", SupportsCredentials: ");
        builder.append(supportsCredentials);
        builder.append(", Origins: {");
        builder.append(String.join(",", origins));
        builder.append("}");
        builder.append(", Methods: {");
        builder.append(String.join(",", methods));
        builder.append("}");
        builder.append(", Headers: {");
        builder.append(String.join(",", headers));
        builder.append("}");
        builder.append(", ExposedHeaders: {");
        builder.append(String.join(",", exposedHeaders));
        builder.append("}");
        return builder.toString();
    }


    public List<String> getExposedHeaders() {
        return exposedHeaders;
    }

    public void setExposedHeaders(List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public boolean isSupportsCredentials() {
        return supportsCredentials;
    }

    public void setSupportsCredentials(boolean supportsCredentials) {
        this.supportsCredentials = supportsCredentials;
    }

    public Predicate<String> getIsOriginAllowed() {
        return isOriginAllowed;
    }

    public void setIsOriginAllowed(Predicate<String> isOriginAllowed) {
        this.isOriginAllowed = isOriginAllowed;
    }

    //todo set to absolute uri
    private static URI CreateDomainUri(String origin)
    {
       return URI.create(origin.replace(_wildcardSubdomain, ""));
    }
}
