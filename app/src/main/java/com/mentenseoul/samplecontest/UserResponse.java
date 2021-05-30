package com.mentenseoul.samplecontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("orderList")
    @Expose
    private List<Data6> orderList = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<Data6> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Data6> orderList) {
        this.orderList = orderList;
    }

}
