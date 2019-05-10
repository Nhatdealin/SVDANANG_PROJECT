package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by dcmen on 9/30/2016.
 */
public class ForgotPasswordInput {
    @SerializedName("email")
    public String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgotPasswordInput(String email) {
        this.email = email;
    }
}
