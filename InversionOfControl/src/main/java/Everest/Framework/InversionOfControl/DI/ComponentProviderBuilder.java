package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.Abstractions.IComponentRegister;
import Everest.Framework.InversionOfControl.IComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.PrincipalDuplicateGetter;

/**
 * Use to build an {@link IComponentProvider}.
 *
 * @author Chendjou
 * @version 1
 * @since 30-04-2019
 */
public class ComponentProviderBuilder {
    private ComponentCollectionBuilder componentCollectionBuilder;
    private DuplicateNameScanner duplicateNameScanner;
    private CircularDependencyDetector circularDependencyDetector;
    private PrincipalDuplicateGetter principalDuplicateGetter;

    public ComponentProviderBuilder() {
        componentCollectionBuilder = new ComponentCollectionBuilder();
        duplicateNameScanner = new DuplicateNameScanner();
        circularDependencyDetector = new CircularDependencyDetector();
        principalDuplicateGetter = new PrincipalDuplicateGetter();
    }

    public IComponentProvider buildProvider(IComponentRegister componentRegister){
        ComponentCollection componentCollection = componentCollectionBuilder.build(componentRegister);
        duplicateNameScanner.checkDuplicateName(componentCollection);
        principalDuplicateGetter.checkDuplicatePrincipal(componentCollection);
        circularDependencyDetector.DetectConstructorCircularDependencies(componentCollection);

        return new ComponentProvider(componentCollection);
    }
}
