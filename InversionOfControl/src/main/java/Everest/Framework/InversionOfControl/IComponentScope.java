package Everest.Framework.InversionOfControl;

import Everest.Framework.InversionOfControl.Abstractions.IDisposable;

/**
 * A service provider scope.
 *
 * @author Chendjou
 * @version 1
 * @since 02-05-2019
 */
public interface IComponentScope extends IDisposable, IComponentProvider {
    IComponentProvider getComponentProvider();
}
