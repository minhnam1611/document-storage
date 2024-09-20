package com.vnpt.common.constants;

public enum ErrorCodes {

    SUCCESS("200", "SUCCESS"),
    NOT_FOUND("400", "NOT_FOUND"),
    UNAUTHORIZED("401", "UNAUTHORIZED"),
    FORBIDDEN("503", "FORBIDDEN"),

    // Auth Error
    USERNAME_NOT_EXIST("001","USERNAME NOT EXIST"),
    EMAIL_NOT_EXIST("002","EMAIL NOT EXIST"),
    IVALID_LOGIN("003", "INVALID USERNAME OR PASSWORD"),
    EMAIL_EXISTED("004", "THIS EMAIL ALREADY EXISTS"),
    USERNAME_EXISTED("005", "THIS USERNAME ALREADY EXISTS"),
    DELETE_USER_FAIL("006", "CAN NOT DELETE CURRENT USER"),


    // User Management Error
    NOT_FOUND_USER("101","USER NOT FOUND"),

    //Role Management Error
    NOT_FOUND_ROLE("201", "ROLE NOT FOUND"),

    ROLE_HAS_USED("202", "ROLE HAS BEEN USED"),

    //Resource Management Error
    NOT_FOUND_RESOURCE("300", "RESOURCE NOT FOUND"),

    SERVER_ERROR("500", "SERVER_ERROR"),

    //Device Error
    DEVICE_NOT_EXIST("601","DEVICE NOT EXIST."),

    DEVICE_USED("602","DEVICE HAS BEEN USED."),

    DEVICE_INFO_SAVED("603", "DEVICE INFORMATION HAS BEEN SAVED"),


    //Tenant Error
    TENANT_NAME_EXISTED("701","TENANT NAME ALREADY EXISTED "),

    TENANT_TOPIC_EXISTED("702","TENANT TOPIC ALREADY EXISTED "),

    //COMPOSITION
    COMPOSITION_NOT_EXIST("801","COMPOSITION NOT EXIST."),

    COMPOSITION_IS_DEFAULT("802", "COMPOSITION IS DEFAULT, CAN NOT UPDATE OR DELETE"),

    //LAYOUT
    LAYOUT_NAME_EXIST("1000","Tên layout đã tồn tại"),
    LAYOUT_CHECK_DELETE("1001","Layout default"),
    LAYOUT_CHECK_UPDATE("1002", "Layout default"),

    //MEDIA
    MEDIA_REQUIRE("1100","MEDIA is required"),
    FILE_REQUIRE("1101","file is required"),
    FILE_TYPE("1102","file type is not support"),
    FILE_MAXLENGTH("1103","File size exceeds the maximum allowed limit"),
    MEDIA_CHECK_DELETE("1104","Media đang được sử dụng")
    ;

    public String code;
    public String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
