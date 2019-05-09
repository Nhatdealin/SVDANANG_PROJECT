package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.ContentBlogPost;
import com.diendan.svdanang.models.ContentProject;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class ProjectOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ContentProject data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContentProject getData() {
        return data;
    }

    public void setData(ContentProject data) {
        this.data = data;
    }

}
