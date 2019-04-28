package Everest.Framework.Mvc.Routing;

import java.util.ArrayList;

/**
 * A Collection which contains all of parameters of a route mapping.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
public class RouteParameters extends ArrayList<RouteParameter> {

    /**
     * Gets a route parameter by its position in the url mapping.
     * @param position The position of the parameter.
     * This is not the position in the collection but the position field of the {@link RouteParameter}.
     * @return The Found parameter.
     * @exception IndexOutOfBoundsException If {@param position} is upper or equals to collection size.
     */
    RouteParameter findByPosition(int position){
        if (position >= size()){
            throw new IndexOutOfBoundsException("Noting route parameter at position " + position);
        }

        return stream().filter(r -> r.getPosition() == position).findFirst().orElse(null);
    }

    /**
     * Find a {@link RouteParameter} by its name.
     * @param name The name of the desired parameter.
     * @return The {@link RouteParameter} which has {@param name} as name.
     * Or {@code null} otherwise.
     */
    RouteParameter findByName(String name){
        return stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Checks if there a {@link RouteParameter} which name is {@param name}.
     * @param name The name of the desired paramater.
     * @return {@code true} If there are a  {@link RouteParameter} which has {@param name} as name.
     */
    boolean contains(String name){
        return stream().anyMatch(r -> r.getName().equals(name));
    }
}
