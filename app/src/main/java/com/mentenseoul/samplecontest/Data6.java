package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data6 {
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("kinds")
    @Expose
    private String kinds;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("grade")
    @Expose
    private Integer grade;
    @SerializedName("orderCount")
    @Expose
    private Integer orderCount;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
