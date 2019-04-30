package Everest.Framework.Http;

import Everest.Framework.Core.Exception.NotSupportedEncodingException;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * ComponentBuilder to parse QueryString and build a {@link Everest.Framework.Http.QueryCollection}
 */
public class QueryCollectionBuilder {
    private static final String UTF_8_ENCODING = "UTF-8";
    private QueryCollection collection = new QueryCollection();
    private String queryString;

    public QueryCollectionBuilder(String queryString) {
        this.queryString = queryString;
    }

    public QueryCollectionBuilder parse(){
        if(StringUtils.isEmpty(queryString)){
            return this;
        }
        if(queryString.equals("?") || queryString.equals("?=")){
            return this;
        }

        // Remove the the eventual '?'
        if(queryString.startsWith("?")){
            queryString = queryString.substring(1);
        }


        String[] keyValues = queryString.split("&");
        for (String keyValue: keyValues){
            String[] keyValuePair= keyValue.split("=");

            if(keyValuePair.length > 0){
                String key = utf8Decode(keyValuePair[0]);
                collection.append(key, new ArrayList<>());

                if(keyValuePair.length == 2){
                    String value =  utf8Decode(keyValuePair[1]);
                    if(!StringUtils.isEmpty(value)){
                        collection.get(key).add(value);
                    }
                }
            }


        }
        return this;
    }

    public QueryCollection getCollection() {
        return collection;
    }
    private String utf8Decode(String value){
        try {
            return URLDecoder.decode(value, UTF_8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new NotSupportedEncodingException(e);
        }
    }
}
