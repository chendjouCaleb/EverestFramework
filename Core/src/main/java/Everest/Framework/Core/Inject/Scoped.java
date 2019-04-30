package Everest.Framework.Core.Inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the decorated target will be a scoped component in DI container.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Scoped { }
