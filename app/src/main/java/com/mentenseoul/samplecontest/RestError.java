package com.mentenseoul.samplecontest;

import com.google.gson.annotations.SerializedName;

public class RestError {
    @SerializedName("status")
    public int status;

    @SerializedName("responseMessage")
    public String responseMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
