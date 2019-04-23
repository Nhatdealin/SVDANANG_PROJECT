package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataBlogPost;
import com.diendan.svdanang.models.DataBlogPostsTopic;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class BlogPostTopicsOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    private DataBlogPostsTopic data;

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

    public DataBlogPostsTopic getData() {
        return data;
    }

    public void setData(DataBlogPostsTopic data) {
        this.data = data;
    }

}
