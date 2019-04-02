package com.diendan.svdanang;

public class Eventitem {
    private int identity,idImage,fee;
    private String title, summary,location,topic;

    public Eventitem(int identity,int idimage, String title, String summary, int fee, String location, String topic) {
        this.identity = identity;
        this.title = title;
        this.summary = summary;
        this.fee = fee;
        this.location = location;
        this.topic = topic;
        this.idImage = idimage;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
