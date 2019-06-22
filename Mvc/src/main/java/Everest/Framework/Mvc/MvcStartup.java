package Everest.Framework.Mvc;


import Everest.Framework.Core.Exception.UnauthorizedException;
import Everest.Framework.Mvc.Cors.CORSConfig;
import Everest.Framework.Mvc.ExceptionHandler.ExceptionStatusCodeGetter;

import java.util.NoSuchElementException;


public abstract class MvcStartup {


    public abstract String getAppBaseUrl();

    public String getWebAppPath(){
        return "/src/main/webapp";
    }

    public String getViewFolderPath(){
        return "/WEB-INF/templates/";
    }

    public String getProjectBaseDir(){
        return System.getProperty("user.dir");
    }

    public String[] getStaticResourceFolders(){
        return new String[] {"/dist/"};
    }

    public abstract CORSConfig corsConfig();

    public abstract void setMiddlewareChain(MiddlewareRegister register);

    public void setExceptionStatusCode(ExceptionStatusCodeGetter statusCodes){
        statusCodes.put(NoSuchElementException.class, 404);
        statusCodes.put(UnauthorizedException.class, 404);
    }
}
