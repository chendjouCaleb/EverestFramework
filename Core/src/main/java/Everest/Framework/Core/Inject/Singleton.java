package Everest.Framework.Core.Inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the decorated target will be a singleton component in DI container.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton { }
