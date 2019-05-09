package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.ContentEvent;
import com.diendan.svdanang.models.ContentProject;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class EventOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ContentEvent data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContentEvent getData() {
        return data;
    }

    public void setData(ContentEvent data) {
        this.data = data;
    }

}
