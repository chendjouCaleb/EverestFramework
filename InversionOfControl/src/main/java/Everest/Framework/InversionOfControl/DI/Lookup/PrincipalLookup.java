package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.ManySelectableException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PrincipalLookup {
    public Component findPrincipal(Class componentType, ComponentCollection components){
        List<Component> collection = components.listByComponentTypes(componentType);


        if (collection.size() == 1) {
            return collection.get(0);
        } else if (collection.size() > 1) {
            List<Component> principals = collection.stream().filter(Component::isPrincipal).collect(Collectors.toList());

            if (principals.size() == 0) {
                throw new NoSuchElementException(
                        String.format("There are to many component with type '%s' but nothing of them is marked as principal",
                                componentType.getName()));
            }
            if (principals.size() > 1) {
                throw new ManySelectableException(
                        String.format("There are to many component with type '%s' marked as principal", componentType.getName()));
            } else {
                return principals.get(0);
            }
        } else {
            throw new NoSuchElementException(String.format("There are component with type '%s'", componentType.getName()));
        }
    }
}
