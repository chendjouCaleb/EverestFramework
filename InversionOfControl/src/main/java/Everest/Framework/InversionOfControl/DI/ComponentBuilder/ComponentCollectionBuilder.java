package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * Uses all {@link ComponentDescriptor} to build the {@link ComponentCollection} of the container.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ComponentCollectionBuilder {
    private Collection<ComponentBuilder> componentBuilders;
    private FactoryMethodComponentBuilder factoryMethodComponentBuilder;

    public ComponentCollectionBuilder(@Nonnull Collection<ComponentBuilder> componentBuilders) {
        this.componentBuilders = componentBuilders;
    }

    /**
     * Builds the {@link ComponentCollection} based on a the specified list of {@link ComponentDescriptor}.
     * @param descriptors The list of description of component.
     * @return The {@link ComponentCollection}
     */
    public ComponentCollection build(Collection<ComponentDescriptor> descriptors){

        ComponentCollection components = new ComponentCollection();
        for(ComponentDescriptor descriptor: descriptors){
            for(ComponentBuilder builder: componentBuilders){
                if(builder.canBuild(descriptor)){
                    components.add(builder.build(descriptor));
                }
            }
        }

        List<TypeComponent> typeComponents = components.typeComponents();

        for(TypeComponent component: typeComponents){
            List<FactoryMethodComponent> factoryMethodComponents = factoryMethodComponentBuilder.buildComponents(component);
            components.addAll(factoryMethodComponents);
        }

        return components;
    }
}
