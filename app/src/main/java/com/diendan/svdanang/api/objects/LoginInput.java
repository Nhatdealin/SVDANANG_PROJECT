package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcmen on 9/30/2016.
 */
public class LoginInput {
    @SerializedName("usernameOrEmail")
    public String usernameOrEmail;
    @SerializedName("password")
    public String password;

    public LoginInput(String usernameOrEmail, String password){
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
}
