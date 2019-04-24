package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataBlogPosts;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class BlogPostsOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBlogPosts data;

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

    public DataBlogPosts getData() {
        return data;
    }

    public void setData(DataBlogPosts data) {
        this.data = data;
    }

}
