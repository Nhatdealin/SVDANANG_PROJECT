package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcmen on 9/30/2016.
 */
public class LoginInput {
    @SerializedName("userNameOrEmail")
    public String userNameOrEmail;
    @SerializedName("password")
    public String password;

    public LoginInput(String usernameOrEmail, String password){
        this.userNameOrEmail = usernameOrEmail;
        this.password = password;
    }
}
