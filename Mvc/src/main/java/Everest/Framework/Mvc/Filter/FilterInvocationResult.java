package Everest.Framework.Mvc.Filter;

public class FilterInvocationResult {
    Object result;
    boolean responseSended;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isResponseSended() {
        return responseSended;
    }

    public void setResponseSended(boolean responseSended) {
        this.responseSended = responseSended;
    }
}
