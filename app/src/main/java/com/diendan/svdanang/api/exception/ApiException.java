package com.diendan.svdanang.api.exception;

import android.content.Context;


/**
 * Created by dcmen on 08/31/16.
 */
public class ApiException extends HrException {
    // Error code
    private int mErrorCode;
    private String mErrorDesc;
    public static final int NETWORK_ERROR = -1;
    public static final int CONVERT_BASE_64_ERROR = -2;
    public static final int FAILURE_SESSION_EXPIRED = 2;
    public static final int INVALID_PHONE_NUMBER = 304;
    public static final int API_ERROR = 1;

    public ApiException(int errorCode, String errorDesc) {
        super(errorDesc);
        mErrorCode = errorCode;
        mErrorDesc = errorDesc;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorDesc() {
        return mErrorDesc;
    }

    @Override
    public String getMessage() {
        return getErrorDesc();
    }

    /**
     * Match errorCode with corresponding string.
     * @param context context to get string from resources
     * @return
     */
    public String getMessage(Context context) {
        switch (mErrorCode) {
            default:
                return mErrorDesc;
        }
    }
}
