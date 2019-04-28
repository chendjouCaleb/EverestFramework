package Everest.Framework.Mvc.Routing;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contains the data found in the route url.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class RouteValues extends HashMap<String, String>{
    private List<String> parameterNames = new ArrayList<>();

    public <T> T get(String name, Class<? extends T> type){
        return (T) ConvertUtils.convert(get(name), type);
    }


}
