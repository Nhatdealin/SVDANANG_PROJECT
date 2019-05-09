package com.diendan.svdanang.api.models;

import com.diendan.svdanang.models.ContentProject;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Admin on 4/13/2017.
 */

public class PaypalDonationOutput extends BaseOutput {
    @SerializedName("redirect_url")
    private String redirectUrl;
    @SerializedName("status")
    private String status;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
