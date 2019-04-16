package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

public class ListSchedule {
    @SerializedName("id")
    private Integer id;
    @SerializedName("eventId")
    private Integer eventId;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("schedule")
    private String schedule;
    @SerializedName("location")
    private String location;
    @SerializedName("status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

