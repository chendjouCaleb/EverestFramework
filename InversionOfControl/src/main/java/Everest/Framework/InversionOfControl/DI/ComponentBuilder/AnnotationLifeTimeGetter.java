package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.Core.Inject.Scope;
import Everest.Framework.Core.Inject.Scoped;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Core.Inject.Transient;
import Everest.Framework.InversionOfControl.Abstractions.ComponentLifetime;

import java.lang.annotation.Annotation;

/**
 * Use to get a {@link ComponentLifetime} of a annotation decorated by {@link Scope} annotation.
 *
 * @see ComponentLifetime
 * @see Scope
 * @see Singleton
 * @see Transient
 * @see Scoped
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class AnnotationLifeTimeGetter {
    /**
     * Gets a {@link ComponentLifetime} corresponding to the specified annotation.
     * @param annotation The annotation to get the {@link ComponentLifetime}.
     * @return a {@link ComponentLifetime} corresponding to the specified annotation.
     * @throws IllegalStateException if the specified annotation is not a scope annotation.
     */
    public ComponentLifetime getLifeTime(Class<? extends Annotation> annotation){
        if(annotation.equals(Singleton.class)){
            return ComponentLifetime.SINGLETON;
        }
        if(annotation.equals(Transient.class)){
            return ComponentLifetime.TRANSIENT;
        }

        if(annotation.equals(Scoped.class)){
            return ComponentLifetime.SCOPED;
        }

        throw new IllegalStateException(String.format("The annotation %s is not a scope annotation", annotation.getName()));
    }
}
