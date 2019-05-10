package com.diendan.svdanang.api.objects;

import com.google.gson.annotations.SerializedName;

public class PaypalDonationInput {
    @SerializedName("amount")
    private String amount;
    @SerializedName("currencyId")
    private Long currencyId;
    @SerializedName("id")
    private Long id;
    @SerializedName("note")
    private String note;
    @SerializedName("projectId")
    private Long projectId;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public PaypalDonationInput(String amount, Long currencyId, Long id, String note, Long projectId) {
        this.amount = amount;
        this.currencyId = currencyId;
        this.id = id;
        this.note = note;
        this.projectId = projectId;
    }
}
