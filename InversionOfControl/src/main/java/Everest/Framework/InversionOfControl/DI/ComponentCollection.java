package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.DI.Abstractions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains all components of the container context.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ComponentCollection extends ArrayList<Component> {
    List<FactoryMethodComponent> factoryMethodComponents() {
        return stream()
                .filter(c -> c.getClass().equals(FactoryMethodComponent.class))
                .map(c -> (FactoryMethodComponent)c)
                .collect(Collectors.toList());
    }

    List<FactoryProviderComponent> factoryProviderComponents() {
        return stream()
                .filter(c -> c.getClass().equals(FactoryProviderComponent.class))
                .map(c -> (FactoryProviderComponent)c)
                .collect(Collectors.toList());
    }

    List<InstanceComponent> instanceComponents() {
        return stream()
                .filter(c -> c.getClass().equals(InstanceComponent.class))
                .map(c -> (InstanceComponent)c)
                .collect(Collectors.toList());
    }

    public List<TypeComponent> typeComponents() {
        return stream()
                .filter(c -> c.getClass().equals(TypeComponent.class))
                .map(c -> (TypeComponent)c)
                .collect(Collectors.toList());
    }

    public List<Class> getComponentTypes(){
        return stream().map(Component::getComponentType).distinct().collect(Collectors.toList());
    }

    public List<Component> listByComponentTypes(Class type){
        return stream().filter(c -> c.getComponentType().equals(type)).collect(Collectors.toList());
    }


    public Component findByName(String name){
        return stream().filter(c -> c.isNamed() && c.getName().equals(name)).findFirst().orElse(null);
    }

    public Component findByComponentType(Class type) {
        return stream().filter(c -> c.getComponentType().equals(type)).findFirst().orElse(null);
    }
}
