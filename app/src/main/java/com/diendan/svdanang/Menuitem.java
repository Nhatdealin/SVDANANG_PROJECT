package com.diendan.svdanang;

public class Menuitem {
    private int identity;
    private String data;
    public Menuitem(int id, String text) {
        this.identity=id;
        this.data = text;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
