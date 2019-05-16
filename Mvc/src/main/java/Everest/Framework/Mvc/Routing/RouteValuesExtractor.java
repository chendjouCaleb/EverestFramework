package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The role of this component is to extract the route values in the url of a incoming HTTP request.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */
@Singleton
public class RouteValuesExtractor {
    public RouteValues extract(String url, RouteDescriptor descriptor){
        RouteValues values = new RouteValues();

        Pattern pattern = Pattern.compile(descriptor.getMappingPattern());
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        int count = matcher.groupCount();

        for(int i = 1; i <= count; i++){
            values.put(descriptor.getParameters().findByPosition(i-1).getName(), matcher.group(i));
        }

        return values;
    }
}
