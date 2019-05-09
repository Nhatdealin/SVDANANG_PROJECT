package com.diendan.svdanang.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 4/13/2017.
 */

public class DataDonationDetailResult implements Serializable {
    @SerializedName("paymentId")
    private String paymentId;
    @SerializedName("token")
    private String token;
    @SerializedName("PayerID")
    private String PayerID;

    public DataDonationDetailResult(String paymentId, String token, String payerID) {
        this.paymentId = paymentId;
        this.token = token;
        this.PayerID = payerID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayerID() {
        return PayerID;
    }

    public void setPayerID(String payerID) {
        PayerID = payerID;
    }
}

