package Everest.Framework.Mvc.Routing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses and collects all parameter values in an url mapping.
 * Ex: For this mapping: "posts/{postId}/comments/{commentId}",
 * we must have [postId, commentId] as parameter names.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
public class RouteValueExtractor {
    public RouteValues extract(RouteDescriptor descriptor, String url){
        RouteValues values = new RouteValues();

            Pattern pattern = Pattern.compile(descriptor.getMappingPattern());
            System.out.println(pattern.pattern());
            Matcher matcher = pattern.matcher(url);
            matcher.find();

            int count = matcher.groupCount();

            for(int i = 1; i <= count; i++){
                values.put(descriptor.getParameters().findByPosition(i-1).getName(), matcher.group(i));
            }



        return values;
    }
}
