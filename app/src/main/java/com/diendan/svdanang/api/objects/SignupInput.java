package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcmen on 9/30/2016.
 */
public class SignupInput {
    @SerializedName("email")
    public String email;
    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("password")
    public String password;
    @SerializedName("username")
    public String username;

    public SignupInput(String email, String firstname, String lastname, String password, String username) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }
}
