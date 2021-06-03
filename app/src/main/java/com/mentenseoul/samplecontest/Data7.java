package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data7 {

    @SerializedName("upPoint")
    @Expose
    private Integer upPoint;
    @SerializedName("answer")
    @Expose
    private Boolean answer;

    public Integer getUpPoint() {
        return upPoint;
    }

    public void setUpPoint(Integer upPoint) {
        this.upPoint = upPoint;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

}