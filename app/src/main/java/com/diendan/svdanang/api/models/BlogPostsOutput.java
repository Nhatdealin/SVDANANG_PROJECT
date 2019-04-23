package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataBlogPost;
import com.diendan.svdanang.models.DataEvents;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class BlogPostsOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBlogPost data;

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

    public DataBlogPost getData() {
        return data;
    }

    public void setData(DataBlogPost data) {
        this.data = data;
    }

}
