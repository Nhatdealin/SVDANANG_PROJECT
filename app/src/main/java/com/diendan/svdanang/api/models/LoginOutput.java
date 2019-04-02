package com.diendan.svdanang.api.models;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Admin on 4/13/2017.
 */

public class LoginOutput extends BaseOutput {

    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("tokenType")
    public String tokenType;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("userName")
    public String userName;
    @SerializedName("email")
    public String email;
    @SerializedName("grantedActions")
    public ArrayList<String> grantedActions;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getGrantedActions() {
        return grantedActions;
    }

    public void setGrantedActions(ArrayList<String> grantedActions) {
        this.grantedActions = grantedActions;
    }
}
