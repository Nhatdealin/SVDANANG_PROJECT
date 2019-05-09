package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Admin on 4/13/2017.
 */

public class ContentProject implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("projectTopic")
    private ProjectTopic projectTopic;
    @SerializedName("createdBy")
    private CreatedBy createdBy;
    @SerializedName("createdAt")
    private Long createdAt;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("goal")
    private BigDecimal goal;
    @SerializedName("raised")
    private BigDecimal raised;
    @SerializedName("numOfDonators")
    private Long numOfDonators;
    @SerializedName("isExpired")
    private Boolean isExpired;
    @SerializedName("isFulfill")
    private Boolean isFulfill;
    @SerializedName("currency")
    private Currency currency;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectTopic getProjectTopic() {
        return projectTopic;
    }

    public void setProjectTopic(ProjectTopic projectTopic) {
        this.projectTopic = projectTopic;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public BigDecimal getRaised() {
        return raised;
    }

    public void setRaised(BigDecimal raised) {
        this.raised = raised;
    }

    public Long getNumOfDonators() {
        return numOfDonators;
    }

    public void setNumOfDonators(Long numOfDonators) {
        this.numOfDonators = numOfDonators;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Boolean getFulfill() {
        return isFulfill;
    }

    public void setFulfill(Boolean fulfill) {
        isFulfill = fulfill;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

