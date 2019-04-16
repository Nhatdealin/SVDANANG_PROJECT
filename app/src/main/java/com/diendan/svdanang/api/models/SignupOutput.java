package com.diendan.svdanang.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by Admin on 4/13/2017.
 */

public class SignupOutput extends BaseOutput {

    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
