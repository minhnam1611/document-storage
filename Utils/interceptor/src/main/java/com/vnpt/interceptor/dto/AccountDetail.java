package com.vnpt.interceptor.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountDetail implements UserDetails {

    private UserPrincipal userInfo;

    public UserPrincipal getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserPrincipal userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userInfo.getRoleInfo().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
