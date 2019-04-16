package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataEvents;
import com.diendan.svdanang.models.DataProjects;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class EventsOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataEvents data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEvents getData() {
        return data;
    }

    public void setData(DataEvents data) {
        this.data = data;
    }

}
