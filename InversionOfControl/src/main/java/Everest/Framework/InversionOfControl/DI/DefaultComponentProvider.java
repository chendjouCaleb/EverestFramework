package Everest.Framework.InversionOfControl.DI;

import Everest.Framework.InversionOfControl.ComponentProvider;

public class DefaultComponentProvider implements ComponentProvider {
    @Override
    public <T> T GetComponent(Class<? extends T> type) {
        return null;
    }
}
