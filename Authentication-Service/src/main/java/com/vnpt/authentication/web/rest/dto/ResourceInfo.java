package com.vnpt.authentication.web.rest.dto;

import java.util.List;

public class ResourceInfo {
    private String roleCode;
    private String roleName;
    private String srcCode;
    private String srcName;
    private String srcPath;
    private List<String> action;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public List<String> getAction() {
        return action;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }
}
