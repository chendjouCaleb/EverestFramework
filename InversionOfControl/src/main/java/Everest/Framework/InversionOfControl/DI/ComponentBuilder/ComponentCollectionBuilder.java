package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.Abstractions.FactoryMethodComponent;
import Everest.Framework.InversionOfControl.DI.Abstractions.TypeComponent;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    private Collection<ComponentBuilder> componentBuilders = new ArrayList<>();
    private FactoryMethodComponentBuilder factoryMethodComponentBuilder;
    private Logger logger = LoggerFactory.getLogger(ComponentCollectionBuilder.class);

    public ComponentCollectionBuilder() {
        factoryMethodComponentBuilder = new FactoryMethodComponentBuilder();
        componentBuilders.add(new TypeComponentBuilder());
        componentBuilders.add(new FactoryProviderComponentBuilder());
        componentBuilders.add(new InstanceComponentBuilder());
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
                    Component component = builder.build(descriptor);
                    components.add(component);
                    logger.info("Component: {}", component.toString());
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
