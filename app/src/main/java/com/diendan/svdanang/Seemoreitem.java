package com.diendan.svdanang;

public class Seemoreitem {
    private int identity;
    private String title, summary;
    private String topic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Seemoreitem(int id, String tt, String tp, String sm) {
        this.identity = id;
        this.title = tt;
        this.topic = tp;
        this.summary = sm;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
