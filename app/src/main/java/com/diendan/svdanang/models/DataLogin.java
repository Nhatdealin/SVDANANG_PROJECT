package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 4/13/2017.
 */

public class DataLogin implements Serializable {
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("tokenType")
    private String tokenType = "";
    @SerializedName("user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
