package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Admin on 4/13/2017.
 */

public class ContentEvent implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("topicId")
    private Integer topicId;
    @SerializedName("currency")
    private Integer currency;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("expectedQuantity")
    private Integer expectedQuantity;
    @SerializedName("fee")
    private Integer fee;
    @SerializedName("image")
    private String image;
    @SerializedName("location")
    private String location;
    @SerializedName("registrationEndTime")
    private Long registrationEndTime;
    @SerializedName("registrationStartTime")
    private Long registrationStartTime;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("listSchedule")
    private List<ListSchedule> listSchedule = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Integer expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(Long registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public Long getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(Long registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public List<ListSchedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<ListSchedule> listSchedule) {
        this.listSchedule = listSchedule;
    }

}

