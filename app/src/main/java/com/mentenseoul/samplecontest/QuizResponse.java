package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private Data5 data;

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

    public Data5 getData() {
        return data;
    }

    public void setData(Data5 data) {
        this.data = data;
    }
}
