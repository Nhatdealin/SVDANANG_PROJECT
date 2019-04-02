package com.diendan.svdanang;

public class Projectitem {
    private int identity,idImage,goal,raised;
    private String title, summary,location,topic;

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getRaised() {
        return raised;
    }

    public void setRaised(int raised) {
        this.raised = raised;
    }

    public Projectitem(int identity, int idimage, String title, String summary, int raisedmn, int goalmn, String topic) {
        this.identity = identity;
        this.title = title;
        this.summary = summary;
        this.goal = goalmn;
        this.raised = raisedmn;
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
