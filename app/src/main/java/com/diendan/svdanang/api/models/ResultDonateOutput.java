package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.DataDonationResult;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class ResultDonateOutput extends BaseOutput {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataDonationResult data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDonationResult getData() {
        return data;
    }

    public void setData(DataDonationResult data) {
        this.data = data;
    }
}
