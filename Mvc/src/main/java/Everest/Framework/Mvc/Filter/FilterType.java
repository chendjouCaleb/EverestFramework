package Everest.Framework.Mvc.Filter;

public enum FilterType {
    OnActionExecuting(0),
    OnActionExecuted(1);

    private int type;

    FilterType(int type) {
        this.type = type;
    }
}
