package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseRequest {
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
    private String grade;

    public PurchaseRequest(String company, String kinds, String model, String grade) {
        this.company = company;
        this.kinds = kinds;
        this.model = model;
        this.grade = grade;
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
