package com.vnpt.authentication.web.rest.response.auth;

public class LoginByGoogleResponse extends LoginResponse{
    private String email;

    private boolean emailExist;

    public boolean isEmailExist() {
        return emailExist;
    }

    public void setEmailExist(boolean emailExist) {
        this.emailExist = emailExist;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
