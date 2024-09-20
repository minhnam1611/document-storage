package com.vnpt.authentication.web.rest.response.manage_user;


import com.vnpt.authentication.entity.UserEntity;

import java.util.List;

public class GetDetailUserResponse {
    private UserEntity userInfo;

    private List<String> roleInfo;

    public UserEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.userInfo = userInfo;
    }

    public List<String> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<String> roleInfo) {
        this.roleInfo = roleInfo;
    }
}
