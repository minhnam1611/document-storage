package com.vnpt.authentication.service;

import com.vnpt.authentication.service.dto.LocalUser;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;

public interface IUserService {
    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

}
