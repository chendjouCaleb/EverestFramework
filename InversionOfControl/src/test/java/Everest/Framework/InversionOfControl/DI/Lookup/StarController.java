package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.Core.Inject.Resolve;
import Everest.Framework.Core.Inject.UseNamed;

import java.util.Date;

public class StarController {
    //interface typed
    private IStarRepository starRepository;

    private StarBuilder starBuilder;

    //directType component
    @Resolve
    private Date date;

    @UseNamed("starDescriptor")
    private StarDescriptor starDescriptor;

    //Injection constructor
    public StarController(IStarRepository starRepository, StarBuilder starBuilder) {
        this.starRepository = starRepository;
        this.starBuilder = starBuilder;
    }

    public IStarRepository getStarRepository() {
        return starRepository;
    }

    public StarBuilder getStarBuilder() {
        return starBuilder;
    }

    @Resolve
    private void count(@UseNamed("count") Integer count){

        System.out.println("setter invocation: " + count);
    }
}
