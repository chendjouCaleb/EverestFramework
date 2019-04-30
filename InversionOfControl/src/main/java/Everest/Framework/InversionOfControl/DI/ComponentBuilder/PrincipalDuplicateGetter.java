package Everest.Framework.InversionOfControl.DI.ComponentBuilder;

import Everest.Framework.InversionOfControl.DI.Abstractions.Component;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Iterable on a {@link Everest.Framework.InversionOfControl.DI.ComponentCollection}
 * and checks that there are two principal component which have the same componentType.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class PrincipalDuplicateGetter {
    public void checkDuplicatePrincipal(ComponentCollection components){
        List<Component> principals = components.stream().filter(Component::isPrincipal).collect(Collectors.toList());

        for(int i = 0; i < principals.size(); i++){
            for (int j = i+1; j < principals.size(); j++){
                if(principals.get(i).getComponentType().equals(principals.get(j).getComponentType())){
                    throw new IllegalStateException(
                            String.format("The component '%s' and component '%s' have a same componentType" +
                                    " '%s', but is marked as Principal",
                                    principals.get(i).instanceProviderToString(),
                                    principals.get(j).instanceProviderToString(),
                                    principals.get(i).getComponentType().getName()));
                }
            }
        }
    }
}
