package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses and found all {@link RouteParameter} in an url mapping.
 * Ex: For this mapping: "posts/{postId}/comments/{commentId}",
 * we must have [postId, commentId] as parameter names.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
@Singleton
public class RouteParameterExtractor {

    /**
     * Parses and gets parameters.
     * @param mapping The url mapping in the form 'posts/{id}'.
     * @return A list containing the parameters.
     */
    RouteParameters extractParameter(String mapping) {
        RouteParameters parameters = new RouteParameters();

        String regex = "\\{([\\w]+)}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(mapping);
        int i = 0;
        while (matcher.find()){
            RouteParameter parameter = new RouteParameter(matcher.group(1), i);
            parameters.add(parameter);
            i++;
        }
        return parameters;
    }
}
