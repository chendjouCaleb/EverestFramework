package Everest.Framework.Mvc.Filter;


public abstract class ActionFilter<T> implements IActionFilter<T> {
    @Override
    public void init(T annotation) { }

}
