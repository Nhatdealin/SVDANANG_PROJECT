package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcmen on 9/30/2016.
 */
public class SignupInput {
    @SerializedName("email")
    public String email;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("password")
    public String password;
    @SerializedName("userName")
    public String userName;

    public SignupInput(String email, String firstname, String lastname, String password, String username) {
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
        this.password = password;
        this.userName = username;
    }
}
