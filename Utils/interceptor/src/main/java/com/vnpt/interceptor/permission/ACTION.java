package com.vnpt.interceptor.permission;

public enum ACTION {

    READ("1"),
    CREATE("2"),
    UPDATE("3"),
    DELETE("4");

    private String action;
    ACTION(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
