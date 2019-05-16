package Everest.Framework.Http;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE"),
    PUT("PUT"),
    OPTIONS("OPTIONS"),
    PATH("PATH");

    private String name;
    HttpMethod(String desc){
        name = desc;
    }

    @Override
    public String toString() {
        return name;
    }
}
