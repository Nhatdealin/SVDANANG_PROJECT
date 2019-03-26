package com.diendan.svdanang;

public class Homeitem {
    private int idtopic,identity1,identity2,identity3,identity4;
    private String data1,data2,data3,data4;
    private String title;

    public Homeitem(int idtp, String til,int id1,int id2,int id3,int id4, String text1, String text2, String text3, String text4) {
        this.idtopic = idtp;
        this.title=til;
        this.identity1=id1;
        this.identity2=id2;
        this.identity3=id3;
        this.identity4=id4;
        this.data1 = text1;
        this.data2 = text2;
        this.data3 = text3;
        this.data4 = text4;
    }

    public String getTitle() {
        return title;
    }

    public int getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(int idtopic) {
        this.idtopic = idtopic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdentity1() {
        return identity1;
    }

    public void setIdentity1(int identity1) {
        this.identity1 = identity1;
    }

    public int getIdentity2() {
        return identity2;
    }

    public void setIdentity2(int identity2) {
        this.identity2 = identity2;
    }

    public int getIdentity3() {
        return identity3;
    }

    public void setIdentity3(int identity3) {
        this.identity3 = identity3;
    }

    public int getIdentity4() {
        return identity4;
    }

    public void setIdentity4(int identity4) {
        this.identity4 = identity4;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }
}
