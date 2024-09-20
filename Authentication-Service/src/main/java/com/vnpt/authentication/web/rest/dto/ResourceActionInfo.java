package com.vnpt.authentication.web.rest.dto;

import java.util.List;

public class ResourceActionInfo {
    private String srcCode;

    private List<String> lstAction;

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
    }

    public List<String> getLstAction() {
        return lstAction;
    }

    public void setLstAction(List<String> lstAction) {
        this.lstAction = lstAction;
    }
}
