package Everest.Framework.Mvc.ValueResolver.TypedResolver;

import Everest.Framework.Http.FormFileCollection;
import Everest.Framework.Http.IFormFile;
import Everest.Framework.Mvc.Action.ActionContext;
import Everest.Framework.Mvc.ValueResolver.ITypeValueResolver;

import java.lang.reflect.Parameter;

/**
 * Provider the first form file of the request files.
 * @see FormFileCollection
 * @see IFormFile
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public class FormFileResolver implements ITypeValueResolver<IFormFile, ActionContext> {

    public IFormFile getValue(ActionContext context, Parameter parameter) {
        FormFileCollection files = context.getHttpContext().getRequest().getFiles();

        if(files == null || files.size() == 0){
            return null;
        }
        return files.get(0);
    }
}
