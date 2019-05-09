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
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("eventTopic")
    private EventTopic eventTopic;
    @SerializedName("currency")
    private Currency currency;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("expectedQuantity")
    private Long expectedQuantity;
    @SerializedName("fee")
    private Long fee;
    @SerializedName("image")
    private String image;
    @SerializedName("location")
    private String location;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("createdBy")
    private CreatedBy createdBy;
    @SerializedName("createdAt")
    private Long createdAt;
    @SerializedName("schedules")
    private List<EventSchedule> schedules = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public EventTopic getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(EventTopic eventTopic) {
        this.eventTopic = eventTopic;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Long expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
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

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public List<EventSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<EventSchedule> schedules) {
        this.schedules = schedules;
    }
}

