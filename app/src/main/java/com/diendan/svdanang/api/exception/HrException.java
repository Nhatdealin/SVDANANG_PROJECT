/**
 * Copyright 2009 Joe LaPenna
 */

package com.diendan.svdanang.api.exception;

/**
 * Created by dcmen on 08/31/16.
 */
public class HrException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private String mExtra;

    public HrException(String message) {
        super(message);
    }

    public HrException(String message, String extra) {
        super(message);
        mExtra = extra;
    }
    
    public String getExtra() {
        return mExtra;
    }
}
