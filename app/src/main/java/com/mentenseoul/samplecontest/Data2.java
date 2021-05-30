package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data2 {
    @SerializedName("fisrtCnt")
    @Expose
    private Integer fisrtCnt;
    @SerializedName("secondCnt")
    @Expose
    private Integer secondCnt;
    @SerializedName("thirdCnt")
    @Expose
    private Integer thirdCnt;
    @SerializedName("fourthCnt")
    @Expose
    private Integer fourthCnt;
    @SerializedName("fivethCnt")
    @Expose
    private Integer fivethCnt;

    public Integer getFisrtCnt() {
        return fisrtCnt;
    }

    public void setFisrtCnt(Integer fisrtCnt) {
        this.fisrtCnt = fisrtCnt;
    }

    public Integer getSecondCnt() {
        return secondCnt;
    }

    public void setSecondCnt(Integer secondCnt) {
        this.secondCnt = secondCnt;
    }

    public Integer getThirdCnt() {
        return thirdCnt;
    }

    public void setThirdCnt(Integer thirdCnt) {
        this.thirdCnt = thirdCnt;
    }

    public Integer getFourthCnt() {
        return fourthCnt;
    }

    public void setFourthCnt(Integer fourthCnt) {
        this.fourthCnt = fourthCnt;
    }

    public Integer getFivethCnt() {
        return fivethCnt;
    }

    public void setFivethCnt(Integer fivethCnt) {
        this.fivethCnt = fivethCnt;
    }
}
