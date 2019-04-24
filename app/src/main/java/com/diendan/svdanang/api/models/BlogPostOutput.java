package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.ContentBlogPost;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class BlogPostOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ContentBlogPost data;

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

    public ContentBlogPost getData() {
        return data;
    }

    public void setData(ContentBlogPost data) {
        this.data = data;
    }

}
