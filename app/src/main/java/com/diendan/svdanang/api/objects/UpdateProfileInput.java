package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dcmen on 9/30/2016.
 */
public class UpdateProfileInput {
    @SerializedName("address")
    private String address;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("birthDate")
    private Long birthDate;
    @SerializedName("city")
    private String city;
    @SerializedName("facebookLink")
    private String facebookLink;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("gender")
    private boolean gender;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public UpdateProfileInput(String address, String avatar, Long birthDate, String city, String facebookLink, String firstName, Boolean gender, String lastName, String phoneNumber) {
        this.address = address;
        this.avatar = avatar;
        this.birthDate = birthDate;
        this.city = city;
        this.facebookLink = facebookLink;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
