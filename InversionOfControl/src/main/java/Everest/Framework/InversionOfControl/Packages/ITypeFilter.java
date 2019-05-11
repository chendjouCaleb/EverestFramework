package Everest.Framework.InversionOfControl.Packages;

import Everest.Framework.Core.Inject.*;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;

import javax.annotation.Nonnull;

public interface ITypeFilter {
    boolean isElligible(Class type);

    default boolean isPrincipal(Class<?> type) {
        return type.isAnnotationPresent(Principal.class);
    }


    default ComponentLifetime getLifeTime(Class<?> type) {
        if (type.isAnnotationPresent(Singleton.class)) {
            return ComponentLifetime.SINGLETON;
        }

        if (type.isAnnotationPresent(Scoped.class)) {
            return ComponentLifetime.SCOPED;
        }

        if (type.isAnnotationPresent(Transient.class)) {
            return ComponentLifetime.TRANSIENT;
        }
        return ComponentLifetime.SINGLETON;
    }

    default String getName(@Nonnull Class<?> type) {
        Named named = type.getAnnotation(Named.class);
        if (named != null && !"".equals(named.value())) {
            return named.value();
        }
        return null;
    }


    default Class<?> getComponentType(@Nonnull Class<?> type) {
        UseType useType = type.getAnnotation(UseType.class);

        if (useType != null) {
            if (!useType.value().isAssignableFrom(type)) {
                throw new IllegalArgumentException(String.format("The type '%s' is not assignable to'%s'.", type, useType.value()));
            }
            return useType.value();
        }

        if (type.getInterfaces().length == 1) {
            return type.getInterfaces()[0];
        }
        if (type.getInterfaces().length > 1) {
            throw new IllegalStateException(
                    String.format("The type '%s' implement multiple interfaces. " +
                            "Use decorator @UseType to set component type", type.getName()));

        }

        if(type.getSuperclass() != null){
            return type.getSuperclass();
        }

        return type;
    }
}
