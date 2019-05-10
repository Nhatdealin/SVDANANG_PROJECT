package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by dcmen on 9/30/2016.
 */
public class AvatarInput {
    @SerializedName("file")
    public File file;


    public AvatarInput(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
