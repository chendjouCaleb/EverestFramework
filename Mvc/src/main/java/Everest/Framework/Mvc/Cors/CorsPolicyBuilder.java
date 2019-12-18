package Everest.Framework.Mvc.Cors;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Exposes methods to build a policy.
 * @author Chendjou
 * @version 1
 * @since 25-08-2019
 */
public class CorsPolicyBuilder {
    private final CorsPolicy _policy = new CorsPolicy();

    public CorsPolicyBuilder(){}
    /**
     * Creates a new instance of the {@link CorsPolicyBuilder}.
     * @param policy The policy which will be used to intialize the builder.
     */
    public CorsPolicyBuilder(CorsPolicy policy)
    {
        Combine(policy);
    }

/**
 * Adds the specified {@param origin }to the policy.
 * @param origins The origins that are allowed.
 * @return The current policy builder.
 *
 * @apiNote  This method normalizes the origin value prior to adding it to {@link CorsPolicy#getOrigins()} to match
 * the normalization performed by the browser on the value sent in the <c>ORIGIN</c> header.
 * <list type="bullet">
 * <item>
 * If the specified origin has an internationalized domain name (IDN), the punycoded value is used. If the origin
 * specifies a default port (e.g. 443 for HTTPS or 80 for HTTP), this will be dropped as part of normalization.
 * Finally, the scheme and punycoded host name are culture invariant lower cased before being added to the <see cref="CorsPolicy.Origins"/>
 * collection.
 * </item>
 * <item>
 * For all other origins, normalization involves performing a culture invariant lower casing of the host name.
 * </item>
 * </list>
 *
 */
    public CorsPolicyBuilder WithOrigins(String... origins)
    {
        for(String origin : origins)
        {
            String normalizedOrigin = GetNormalizedOrigin(origin);
            _policy.getOrigins().add(normalizedOrigin);
        }

        return this;
    }

    public static String GetNormalizedOrigin(String origin)
    {
        return origin;
    }

    /// <summary>
    /// Adds the specified {@params to the policy.
    /// </summary>
    /// <param name="headers"></param>
    /// <returns>T</returns>

    /**
     * Adds the specified {@param headers} to the policy.
     * @param headers The headers which need to be allowed in the request.
     * @return he current policy builder.
     */
    public CorsPolicyBuilder WithHeaders(String... headers)
    {
        for(String req:headers)
        {
            _policy.getHeaders().add(req);
        }
        return this;
    }

    /**
     * Adds the specified {@param exposedHeaders} to the policy.
     * @param exposedHeaders The headers which need to be exposed to the client.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder WithExposedHeaders(String... exposedHeaders)
    {
        for(String req:exposedHeaders)
        {
            _policy.getExposedHeaders().add(req);
        }

        return this;
    }

    /**
     * Adds the specified {@param methods} to the policy.
     * @param methods The methods which need to be added to the policy.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder WithMethods(String... methods)
    {
        for(String req: methods)
        {
            _policy.getMethods().add(req);
        }

        return this;
    }

    /**
     * Sets the policy to allow credentials.
     * @return he current policy builder.
     */
    public CorsPolicyBuilder AllowCredentials()
    {
        _policy.setSupportsCredentials(true);
        return this;
    }

    /**
     * Sets the policy to not allow credentials.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder DisallowCredentials()
    {
        _policy.setSupportsCredentials(false);
        return this;
    }

    /**
     * Ensures that the policy allows any origin.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder AllowAnyOrigin()
    {
        _policy.getOrigins().clear();
        _policy.getOrigins().add(CorsConstants.AnyOrigin);
        return this;
    }

    /**
     * Ensures that the policy allows any method.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder AllowAnyMethod()
    {
        _policy.getMethods().clear();
        _policy.getMethods().add("*");
        return this;
    }

    /// <summary>
    ///
    /// </summary>
    /// <returns>The current policy builder.</returns>

    /**
     * Ensures that the policy allows any header.
     * @return The current policy builder.
     */
    public CorsPolicyBuilder AllowAnyHeader()
    {
        _policy.getHeaders().clear();
        _policy.getHeaders().add("*");
        return this;
    }


    /// <summary>
    ///
    /// </summary>
    /// <param name="isOriginAllowed"></param>
    /// <returns>The current policy builder.</returns>

    /**
     * Sets the specified {@param isOriginAllowed} for the underlying policy.
     * @param isOriginAllowed The function used by the policy to evaluate if an origin is allowed.
     *  @return The current policy builder.
     */
    public CorsPolicyBuilder SetIsOriginAllowed(Predicate<String> isOriginAllowed)
    {
        _policy.setIsOriginAllowed(isOriginAllowed);
        return this;
    }

    /**
     *Sets the {@link CorsPolicy#getIsOriginAllowed() } property of the policy to be a function
     * that allows origins to match a configured wildcarded domain when evaluating if the
     * origin is allowed.
     *  @return The current policy builder.
     */

    //todo A faire
    public CorsPolicyBuilder setIsOriginAllowedToAllowWildcardSubdomains()
    {
        //_policy.setIsOriginAllowed(() ->_policy.isOriginAnAllowedSubdomain());
        return this;
    }

    /**
     * Builds a new {@link CorsPolicy} using the entries added.
     * @return The constructed {@link CorsPolicy}.
     */
    public CorsPolicy Build()
    {
        return _policy;
    }

    /**
     * Combines the given {@param policy} to the existing properties in the builder.
     * @param policy The policy which needs to be combined.
     * @return The current policy builder.
     */
    private CorsPolicyBuilder Combine(CorsPolicy policy)
    {
        Objects.requireNonNull(policy);

        policy.getOrigins().forEach(this::WithOrigins);
        policy.getHeaders().forEach(this::WithHeaders);
        policy.getExposedHeaders().forEach(this::WithExposedHeaders);
        policy.getMethods().forEach(this::WithMethods);

        SetIsOriginAllowed(policy.getIsOriginAllowed());

        if (policy.isSupportsCredentials())
        {
            AllowCredentials();
        }
        else
        {
            DisallowCredentials();
        }

        return this;
    }
}
