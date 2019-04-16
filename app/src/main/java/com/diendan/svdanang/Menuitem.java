package com.diendan.svdanang;

public class Menuitem {

    private int identity,idImage,type;
    private String data;
    public Menuitem(int tp, int id,int idimg, String text) {
        this.type = tp;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }
}
