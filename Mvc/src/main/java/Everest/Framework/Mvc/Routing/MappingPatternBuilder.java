package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Singleton;

/**
 * The responsibility of this class is to transform simple route mapping into a java RegExp.
 *
 * @author Chendjou
 * @version 1
 * @since 27-04-2019
 */
@Singleton
public class MappingPatternBuilder {
    /**
     * Transforms simple route mapping into a java RegExp pattern.
     * @param mapping The simple route mapping.
     * @return The transformed java RegExp
     */
    public String getPattern(String mapping) {
        String regex = "\\{[\\w]+}";
        String replacement = "([\\\\w]+)";

        return mapping.replaceAll(regex, replacement);
    }
}
