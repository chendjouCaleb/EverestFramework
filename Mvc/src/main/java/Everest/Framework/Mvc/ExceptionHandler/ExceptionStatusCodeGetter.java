package Everest.Framework.Mvc.ExceptionHandler;



import Everest.Framework.Core.Inject.Instance;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Store and provide a statusCode for unreachable exception class declaration.
 * And Exception that dont have an {@link IExceptionHandler}.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Instance
public class ExceptionStatusCodeGetter {

    private HashMap<Class<? extends Throwable>, Integer> store = new HashMap<>();
    public Integer get(Class<? extends Throwable> key) {
        if(!store.containsKey(key)){
            throw new NoSuchElementException("The are no status code for the " + key + " class.");
        }
        return store.get(key);
    }


    public Integer put(Class<? extends Throwable> key, Integer value) {
        return store.put(key, value);
    }
}
