package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data3 {
    @SerializedName("userPoint")
    @Expose
    private Integer userPoint;
    @SerializedName("userRank")
    @Expose
    private String userRank;
    @SerializedName("userDiscount")
    @Expose
    private Integer userDiscount;

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public Integer getUserDiscount() {
        return userDiscount;
    }

    public void setUserDiscount(Integer userDiscount) {
        this.userDiscount = userDiscount;
    }
}
