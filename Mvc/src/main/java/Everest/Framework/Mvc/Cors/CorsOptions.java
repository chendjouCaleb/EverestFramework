package Everest.Framework.Mvc.Cors;

import Everest.Framework.Core.Exception.NullArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Provides programmatic configuration for Cors.
 *
 * @author Chendjou
 * @version 1
 * @since 25-08-2019
 */
public class CorsOptions {
    private String _defaultPolicyName = "__DefaultCorsPolicy";
    private Map<String, CorsPolicy> policyMap = new HashMap<>();

    public void setDefaultPolicyName(String value) {
        if (value == null) {
            throw new NullArgumentException("Cannot set default policy name with null value");
        }

        _defaultPolicyName = value;
    }

    /**
     * Adds a new policy and sets it as the default.
     * @param policy The {@link CorsPolicy} policy to be added.
     */
    public void addDefaultPolicy(CorsPolicy policy) {
        if (policy == null) {
            throw new NullArgumentException("Cannot add null value as policy");
        }

        addPolicy(getDefaultPolicyName(), policy);
    }

    /**
     * Adds a new policy and sets it as the default.
     * @param configurePolicy A delegate which can use a policy builder to build a policy.
     */
    public void addDefaultPolicy(Consumer<CorsPolicyBuilder> configurePolicy) {
        Objects.requireNonNull(configurePolicy);

        addPolicy(_defaultPolicyName, configurePolicy);
    }

    /**
     * Adds a new policy.
     * @param name The name of the policy.
     * @param policy The {@link CorsPolicy} policy to be added.
     */
    public void addPolicy(String name, CorsPolicy policy) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(policy);

        policyMap.put(name, policy);
    }

    /**
     * Adds a new policy.
     * @param name The name of the policy.
     * @param configurePolicy A delegate which can use a policy builder to build a policy.
     */
    public void addPolicy(String name, Consumer<CorsPolicyBuilder> configurePolicy) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(configurePolicy);

        CorsPolicyBuilder policyBuilder = new CorsPolicyBuilder();
        configurePolicy.accept(policyBuilder);
        policyMap.put(name,policyBuilder.Build());
    }

    /**
     * Gets the policy based on the {@param name}.
     * @param name The name of the policy to lookup.
     * @return The {@link CorsPolicy} if the policy was added.{@code null} otherwise.
     */
    public CorsPolicy getPolicy(String name) {
        Objects.requireNonNull(name);

        return policyMap.getOrDefault(name, null);
    }

    public Map<String, CorsPolicy> getPolicyMap() {
        return policyMap;
    }

    public String getDefaultPolicyName() {
        return _defaultPolicyName;
    }
}
