package Everest.Framework.InversionOfControl.DI.Lookup;

public class StarRepository implements IStarRepository
{
    Integer count;

    public StarRepository(Integer count) {
        this.count = count;
    }
}
