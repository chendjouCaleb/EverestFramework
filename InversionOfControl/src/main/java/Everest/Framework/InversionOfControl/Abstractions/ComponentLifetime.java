package Everest.Framework.InversionOfControl.Abstractions;

/**
 * Represents the lifetime of an instance component in the container.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public enum ComponentLifetime {

    /**
     * There new instance of component is build when the container build a new scope.
     */
    SCOPED("SCOPED"),

    /**
     * There are only one instance of the component in the container context lifeCycle.
     */
    SINGLETON("SINGLETON"),

    /**
     * Builds a new instance when the component is requested.
     */
    TRANSIENT("TRANSIENT");
    public String lifeTime;

    ComponentLifetime(String lifeTime) {
        this.lifeTime = lifeTime;
    }
}
