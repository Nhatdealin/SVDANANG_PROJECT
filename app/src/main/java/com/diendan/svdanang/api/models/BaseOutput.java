package com.diendan.svdanang.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dcmen on 8/31/2016.
 */
public class BaseOutput implements Serializable {
    @SerializedName("success")
    public boolean success;
    @SerializedName("errorCode")
    public String ErrorCode;
    @SerializedName("errorMessage")
    public String errorMessage;
}
