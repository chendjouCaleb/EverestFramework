package Everest.Framework.Core;

/**
 * A service provider scope.
 *
 * @author Chendjou
 * @version 1
 * @since 02-05-2019
 */
public interface IComponentScope extends IComponentProvider {
    IComponentProvider getComponentProvider();
}
