package Everest.Framework.Mvc.Routing;

/**
 * The description of an parameter of a route mapping.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
public class RouteParameter {
    /**
     * The name of the parameter.
     * Ex: In mapping users/{userId} a parameter name is userId
     */
    private String name;

    /**
     * The position of the parameter in the mapping.
     * The first parameter of the mapping is at position 0.
     *
     * Ex: In mapping users/{userId}/{postId} a parameter name is at position 0,
     * the parameter postId at position 1.
     */
    private int position;

    public RouteParameter() {
    }

    public RouteParameter(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "RouteParameter{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
