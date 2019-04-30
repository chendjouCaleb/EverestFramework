package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to verify that there are no many components with same name.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class DuplicateNameScanner {
    public void checkDuplicateName(ComponentCollection components) {
        List<Component> namedComponents = components.stream().filter(Component::isNamed).collect(Collectors.toList());
        for(int i = 0; i < namedComponents.size(); i++){
            for (int j = i+1; j < namedComponents.size(); j++){
                if(namedComponents.get(i).getName().equals(namedComponents.get(j).getName())){
                    throw new IllegalStateException(
                            String.format("Component '%s' and component '%s' have the same name",
                            namedComponents.get(i).instanceProviderToString(),
                            namedComponents.get(j).instanceProviderToString()));
                }
            }
        }
    }
}
