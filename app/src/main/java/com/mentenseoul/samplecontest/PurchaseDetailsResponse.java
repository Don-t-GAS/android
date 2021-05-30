package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PurchaseDetailsResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private List<Data4> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Data4> getData() {
        return data;
    }

    public void setData(List<Data4> data) {
        this.data = data;
    }
}
