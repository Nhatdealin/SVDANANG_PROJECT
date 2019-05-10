package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataProfile;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class AvatarOutput extends BaseOutput {

    @SerializedName("fileDownloadUri")
    private String fileDownloadUri;
    @SerializedName("fileName")
    private String fileName;
    @SerializedName("fileType")
    private String fileType;
    @SerializedName("size")
    private Integer size;

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
