package com.diendan.svdanang;

public class Menuitem {
    private int identity,idImage;
    private String data;
    public Menuitem(int id,int idimg, String text) {
        this.identity=id;
        this.data = text;
        this.idImage = idimg;
    }

    public int getIdentity() {
        return identity;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
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
