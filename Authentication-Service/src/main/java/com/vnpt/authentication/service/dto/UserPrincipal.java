package com.vnpt.authentication.service.dto;

import java.util.List;

public class UserPrincipal {

    private String username;

    private String email;

    private String phone;

    private TenantInfo tenantInfo;

    private List<String> roleInfo;

    private List<PrincipalInfo> principalInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TenantInfo getTenantInfo() {
        return tenantInfo;
    }

    public void setTenantInfo(TenantInfo tenantInfo) {
        this.tenantInfo = tenantInfo;
    }

    public List<String> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<String> roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<PrincipalInfo> getPrincipalInfo() {
        return principalInfo;
    }

    public void setPrincipalInfo(List<PrincipalInfo> principalInfo) {
        this.principalInfo = principalInfo;
    }
}
