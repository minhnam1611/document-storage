package com.vnpt.authentication.web.rest.request.manage_role;


import com.vnpt.authentication.web.rest.dto.ResourceActionInfo;

import java.util.List;

public class CreateRoleRequest {

    private String code;
    private String name;
    private String desc;

    private List<ResourceActionInfo> srcList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ResourceActionInfo> getSrcList() {
        return srcList;
    }

    public void setSrcList(List<ResourceActionInfo> srcList) {
        this.srcList = srcList;
    }
}
