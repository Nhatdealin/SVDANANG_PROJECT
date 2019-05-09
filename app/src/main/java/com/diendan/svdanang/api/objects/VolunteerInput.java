package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

public class VolunteerInput {
    @SerializedName("address")
    private String address;
    @SerializedName("birthDate")
    private Long birthDate;
    @SerializedName("email")
    private String email;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("gender")
    private Boolean gender;
    @SerializedName("note")
    private String note;
    @SerializedName("office")
    private String office;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("skills")
    private String skills;

    public VolunteerInput(String address, Long birthDate, String email, String facebook, String fullName, Boolean gender, String note, String office, String phoneNumber, String skills) {
        this.address = address;
        this.birthDate = birthDate;
        this.email = email;
        this.facebook = facebook;
        this.fullName = fullName;
        this.gender = gender;
        this.note = note;
        this.office = office;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
