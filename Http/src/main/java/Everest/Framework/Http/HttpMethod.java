package Everest.Framework.Http;


import org.apache.commons.lang3.StringUtils;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE"),
    PUT("PUT"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH"),
    CONNECT("CONNECT"),
    HEAD("HEAD"),
    TRACE("TRACE");


    private String name;
    HttpMethod(String desc){
        name = desc;
    }

    public boolean isTrace(String method){
        return StringUtils.equalsIgnoreCase(TRACE.name, method);
    }

    public boolean isHead(String method){
        return StringUtils.equalsIgnoreCase(HEAD.name, method);
    }

    public boolean isPatch(String method){
        return StringUtils.equalsIgnoreCase(PATCH.name, method);
    }

    public boolean isConnect(String method){
        return StringUtils.equalsIgnoreCase(CONNECT.name, method);
    }


    public boolean isGet(String method){
        return StringUtils.equalsIgnoreCase(GET.name, method);
    }

    public boolean isPost(String method){
        return StringUtils.equalsIgnoreCase(POST.name, method);
    }

    public boolean isDelete(String method){
        return StringUtils.equalsIgnoreCase(DELETE.name, method);
    }

    public boolean isPut(String method){
        return StringUtils.equalsIgnoreCase(PUT.name, method);
    }

    public boolean isOptions(String method){
        return StringUtils.equalsIgnoreCase(OPTIONS.name, method);
    }

    @Override
    public String toString() {
        return name;
    }
}
