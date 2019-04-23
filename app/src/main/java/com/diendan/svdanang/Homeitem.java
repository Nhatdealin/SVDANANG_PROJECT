package com.diendan.svdanang;

import com.diendan.svdanang.models.BlogPostTopic;

public class Homeitem {
    private BlogPostTopic topic;
    private DataHomeitem data1,data2,data3,data4;
    private String title;

    public Homeitem(BlogPostTopic topic, DataHomeitem data1, DataHomeitem data2, DataHomeitem data3, DataHomeitem data4, String title) {
        this.topic = topic;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.title = title;
    }

    public BlogPostTopic getTopic() {
        return topic;
    }

    public void setTopic(BlogPostTopic topic) {
        this.topic = topic;
    }

    public DataHomeitem getData1() {
        return data1;
    }

    public void setData1(DataHomeitem data1) {
        this.data1 = data1;
    }

    public DataHomeitem getData2() {
        return data2;
    }

    public void setData2(DataHomeitem data2) {
        this.data2 = data2;
    }

    public DataHomeitem getData3() {
        return data3;
    }

    public void setData3(DataHomeitem data3) {
        this.data3 = data3;
    }

    public DataHomeitem getData4() {
        return data4;
    }

    public void setData4(DataHomeitem data4) {
        this.data4 = data4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
