package Everest.Framework.Http;


import javax.annotation.Nonnull;
import java.util.*;

/**
 * Represents HttpRequest and HttpResponse headers
 * @author Chendjou
 */
public class HeaderCollection{

    private final String[] emptyKeys = new String[]{};
    private final String emptyValue = "";

    public HeaderCollection(){}

    public HeaderCollection(Map<String, List<String>> store){
        this.store = store;
    }

    private void ensureStore(int capacity){
        store = new HashMap<>(capacity);
    }



    private Map<String, List<String>> store = new HashMap<>();
    /**
     * * Strongly typed access to the Content-Length header.
     *  * DI must keep this in sync with the string representation.
     * @return The size of a content-Length
     */
    public long contentLength(){
        return Long.valueOf(getHeader("content-length"));
    }


    public int size() {
        return store.size();
    }


    public boolean isEmpty() {
        return store.isEmpty();
    }


    public boolean containsHeader(String name) {
        return store.containsKey(name);
    }


    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    public List<String> getHeaders(String key) {
        return store.get(key);
    }

    public String getHeader(String name){
        if(!store.containsKey(name)){
            return null;
        }
        List<String> values = store.get(name);
        if(values == null || values.size() == 0){
            return null;
        }
        return values.get(0);
    }


    /**
     * Adds th value to the corresponding header field.
     * @param name The header name.
     * @param value The header value.
     */
    public void addHeader(String name, String value) {
        if(name == null){
            throw new NullPointerException("Cannot add header with null or empty name");
        }

        if(!store.containsKey(name) || store.get(name) == null){
            store.put(name, new ArrayList<>());
        }

        store.get(name).add(value);
    }

    public void addHeaders(String name, Iterator<String> values){
        if(name == null){
            throw new NullPointerException("Cannot add header with null or empty name");
        }

        if(values == null){
            throw new NullPointerException("Cannot add header with null or empty values");
        }

        if(!store.containsKey(name) || store.get(name) == null){
            store.put(name, new ArrayList<>());
        }

        while(values.hasNext()){
            store.get(name).add(values.next());
        }
    }


    /**
     * Sets th value to the corresponding header field.
     * @param name The header name.
     * @param value The header value.
     */
    public void setHeader(String name, String value) {
        if(name == null){
            throw new NullPointerException("Cannot set header with null or empty name");
        }

        store.put(name, new ArrayList<>());
        store.get(name).add(value);
    }

    public void setHeaders(String name, Iterator<String> values){
        if(name == null){
            throw new NullPointerException("Cannot set header with null or empty name");
        }

        if(values == null){
            throw new NullPointerException("Cannot set header with null or empty values");
        }

        List<String> headers = new ArrayList<>();

        while(values.hasNext()){
            headers.add(values.next());
        }

        store.put(name, headers);
    }

    

    public void remove(String key) {
       store.remove(key);
    }


    public void addAll(Map<? extends String, ? extends List<String>> m) {
        store.putAll(m);
    }


    public void clear() {
        store.clear();
    }

    @Nonnull
    public Set<String> keySet() {
        return store.keySet();
    }

    @Nonnull
    public Collection<List<String>> values() {
        return store.values();
    }

    @Nonnull
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return store.entrySet();
    }
}
