package com.mentenseoul.samplecontest;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("loginId")
    public String loginId;

    @SerializedName("password")
    public String password;

    public LoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
