package com.vnpt.authentication.web.rest.response.auth;


import com.vnpt.authentication.entity.UserEntity;

public class RegisterResponse {

    private UserEntity userInfo;

    public UserEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.userInfo = userInfo;
    }
}
