package Everest.Framework.Core.Exception;

import java.io.UnsupportedEncodingException;

/**
 * No verified exception to wrap the {@code java.io.UnsupportedEncodingException}
 *
 * @author Chendjou
 * @since 15-04-2019
 */
public class NotSupportedEncodingException extends RuntimeException {

    public NotSupportedEncodingException(UnsupportedEncodingException e){
        super(e);
    }
}
