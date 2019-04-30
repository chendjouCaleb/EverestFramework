package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Resolve;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The implementation of {@link ComponentBuilder} which build a {@link TypeComponent}.
 *
 * @see Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentBuilder
 * @see TypeComponent
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class TypeComponentBuilder implements ComponentBuilder<TypeComponent> {

    private InjectionMethodGetter injectionMethodGetter = new InjectionMethodGetter();
    private InjectionConstructorGetter injectionConstructorGetter = new InjectionConstructorGetter();
    private InjectionFieldGetter injectionFieldGetter = new InjectionFieldGetter();

    /**
     * The builder can build a {@link TypeComponent} if the specified descriptor have non null instance.
     * @param descriptor The descriptor which contains information about a component.
     * @return {@code true} if can build an {@link TypeComponent}.
     */
    @Override
    public boolean canBuild(ComponentDescriptor descriptor) {
        return descriptor.getImplementationType() != null;
    }

    @Override
    public TypeComponent build(ComponentDescriptor descriptor) {
        TypeComponent component = new TypeComponent(descriptor);
        return component;
    }

}
