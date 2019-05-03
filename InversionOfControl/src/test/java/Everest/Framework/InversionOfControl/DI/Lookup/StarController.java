package Everest.Framework.InversionOfControl.DI.Lookup;

class StarController {
    private IStarRepository starRepository;
    private StarBuilder starBuilder;

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
}
