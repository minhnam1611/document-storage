package com.vnpt.authentication.service;


import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.vnpt.authentication.config.jwt.TokenProviderJWT;
import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.entity.UserEntity;
import com.vnpt.authentication.entity.UserRoleRelEntity;
import com.vnpt.authentication.repository.RoleRepository;
import com.vnpt.authentication.repository.UserRepository;
import com.vnpt.authentication.repository.UserRoleRelRepository;
import com.vnpt.authentication.service.dto.GooglePojo;
import com.vnpt.authentication.web.rest.response.auth.LoginByGoogleResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.constants.Constant;
import com.vnpt.common.constants.ErrorCodes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class GoogleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenProviderJWT tokenProviderJWT;

    @Autowired
    private UserRoleRelRepository userRoleRelRepository;


//    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String GOOGLE_TOKEN_URI;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_REDIRECT_URI;

//    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String GOOGLE_USER_INFO_URI;

    public String exchangeAuthorizationCode(String authorizationCode) throws IOException {
        String tokenEndpoint = GOOGLE_TOKEN_URI;
        String requestBody = "code=" + URLEncoder.encode(authorizationCode, "UTF-8") +
            "&client_id=" + URLEncoder.encode(GOOGLE_CLIENT_ID, "UTF-8") +
            "&client_secret=" + URLEncoder.encode(GOOGLE_CLIENT_SECRET, "UTF-8") +
            "&redirect_uri=" + URLEncoder.encode(GOOGLE_REDIRECT_URI, "UTF-8") +
            "&grant_type=authorization_code";

        URL url = new URL(tokenEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        connection.getOutputStream().write(requestBody.getBytes("UTF-8"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return parseAccessToken(response.toString());
    }

    public GooglePojo getUserInfo(String accessToken) throws IOException {

        URL url = new URL(GOOGLE_USER_INFO_URI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return parseUserInfo(response.toString());
    }

    private static String parseAccessToken(String jsonResponse) {
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }

    private GooglePojo parseUserInfo(String jsonResponse) {

        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        GooglePojo userInfo = new GooglePojo();
        userInfo.setEmail(jsonObject.get("email").getAsString());
        userInfo.setVerified_email(jsonObject.get("verified_email").getAsBoolean());
        return userInfo;

    }

    public ResponseEntity<BaseResponse<LoginByGoogleResponse>> loginWithGoogle(HttpServletRequest request) throws IOException {
        LoginByGoogleResponse data = new LoginByGoogleResponse();
        BaseResponse<LoginByGoogleResponse> response = new BaseResponse<>();
        //get Code from Google
        String code = request.getParameter("code");
        //get Token
        String accessToken = exchangeAuthorizationCode(code);
        //get UserInfo : email
        GooglePojo userInfo = getUserInfo(accessToken);

        if(userInfo == null ){
            return null;
        }
        String email = userInfo.getEmail();

        //check email
        UserEntity user = userRepository.findByEmail(email);
        if(user == null){
            // create user
            user = new UserEntity();
            user.setUsername(email);
            user.setStatus(Constant.STATUS_ACTIVE);
            user.setEmail(email);
            user.setCreatedBy(Constant.USER);

            RoleEntity role = roleRepository.findByCode(Constant.ROLE_DEFAULT);
            if(role == null || !role.getStatus().equals(Constant.STATUS_ACTIVE)){
                response.setCode(ErrorCodes.SERVER_ERROR.code);
                response.setDesc(ErrorCodes.SERVER_ERROR.message);
                return ResponseEntity.ok(response);
            }
            UserEntity userCreated = userRepository.save(user);
            UserRoleRelEntity userRoleRelEntity = new UserRoleRelEntity();
            userRoleRelEntity.setRoleId(role.getId());
            userRoleRelEntity.setUserId(userCreated.getId());
            userRoleRelEntity.setCreatedBy(Constant.USER);
            userRoleRelRepository.save(userRoleRelEntity);
            data.setEmail(email);
            data.setEmailExist(true);
            data.setToken(tokenProviderJWT.generateToken(user.getUsername()));
            data.setRefreshToken(tokenProviderJWT.generateRefreshToken(user.getUsername()));
            response.success(data);
        }else {
            //doLogin
            data.setEmail(email);
            data.setEmailExist(true);
            data.setToken(tokenProviderJWT.generateToken(user.getUsername()));
            data.setRefreshToken(tokenProviderJWT.generateRefreshToken(user.getUsername()));
            response.success(data);
        }

        return ResponseEntity.ok(response);
    }

}
