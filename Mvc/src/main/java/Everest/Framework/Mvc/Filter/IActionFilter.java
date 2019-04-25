package Everest.Framework.Mvc.Filter;

public interface IActionFilter<T> {
    void init(T annotation);
}
