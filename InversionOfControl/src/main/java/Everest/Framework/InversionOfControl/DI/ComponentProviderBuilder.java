package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.Abstractions.IComponentRegister;
import Everest.Framework.InversionOfControl.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.PrincipalDuplicateGetter;

/**
 * Use to build an {@link ComponentProvider}.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class ComponentProviderBuilder {
    private ComponentCollectionBuilder componentCollectionBuilder;
    private DuplicateNameScanner duplicateNameScanner;
    private CircularDependencyDetector circularDependencyDetector;
    private DependencyDetector dependencyDetector;
    private PrincipalDuplicateGetter principalDuplicateGetter;

    public ComponentProvider buildProvider(IComponentRegister componentRegister){
        ComponentCollection componentCollection = componentCollectionBuilder.build(componentRegister);
        duplicateNameScanner.checkDuplicateName(componentCollection);
        principalDuplicateGetter.checkDuplicatePrincipal(componentCollection);
        dependencyDetector.detectDependencies(componentCollection);
        circularDependencyDetector.DetectConstructorCircularDependencies(componentCollection);

        DefaultComponentProvider componentProvider = new DefaultComponentProvider();

        return componentProvider;
    }
}
