package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataProfile;
import com.diendan.svdanang.models.DataProjects;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class ProjectsOutput extends BaseOutput {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataProjects data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataProjects getData() {
        return data;
    }

    public void setData(DataProjects data) {
        this.data = data;
    }

}
