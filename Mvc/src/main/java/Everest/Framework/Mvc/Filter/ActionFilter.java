package Everest.Framework.Mvc.Filter;


import java.lang.annotation.Annotation;

public abstract class ActionFilter<T extends Annotation> implements IActionFilter<T> {
    @Override
    public void init(T annotation) { }

}
