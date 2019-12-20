package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentBuilder.InjectionConstructorGetter;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class LookupEngineTest {

    private InjectionConstructorGetter constructorGetter;
    private ComponentCollection components = new ComponentCollection();
    private ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
    private LookupEngine lookupEngine;
    private ComponentProvider componentProvider;

    @BeforeEach
    void setUp() {
        ComponentRegister register = new ComponentRegister();

        register.addTransient(StarController.class);
        register.addTransient(IStarRepository.class, StarRepository.class);
        register.addSingleton(StarBuilder.class);
        register.addSingleton(new Date());
        register.add().setInstanceType(Integer.class).setName("count").setValue(10).regist();
        components = builder.build(register);

        componentProvider = new ComponentProvider(new ComponentCollectionBuilder().build(register));
        lookupEngine = componentProvider.getLookupEngine();
    }

    @Test
    void addSingleton() {
    }

    @Test
    void addScoped() {
    }


    void look() {
        StarController controller = (StarController) lookupEngine.look(StarController.class);
        System.out.println(controller);
        System.out.println(controller.getStarBuilder());
        System.out.println(controller.getStarRepository());
    }

}