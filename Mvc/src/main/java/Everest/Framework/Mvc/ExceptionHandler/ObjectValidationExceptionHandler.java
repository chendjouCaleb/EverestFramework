package Everest.Framework.Mvc.ExceptionHandler;


import Everest.Framework.Core.Exception.ObjectValidationException;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Http.StatusCode;
import Everest.Framework.Mvc.Result.EntityResult;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Handle the exception raised by a the failed validation of request data.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
@Singleton
public class ObjectValidationExceptionHandler implements IExceptionHandler {

    @Override
    public Collection<Class<? extends Throwable>> getExceptionTypes() {
        ArrayList<Class<? extends Throwable>> list = new ArrayList<>();
        list.add(ObjectValidationException.class);
        return list;
    }

    @Override
    public void handleException(ExceptionContext context) {
        ValidationErrorModel model = new ValidationErrorModel((ObjectValidationException) context.getException());


        context.setResult(new EntityResult<>(model).setStatusCode(StatusCode.BAD_REQUEST));
    }
}
