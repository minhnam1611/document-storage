package com.vnpt.authentication.controller;

import com.vnpt.authentication.service.AuthService;
import com.vnpt.authentication.service.GoogleService;
import com.vnpt.authentication.service.dto.UserPrincipal;
import com.vnpt.authentication.web.rest.request.auth.LoginRequest;
import com.vnpt.authentication.web.rest.request.auth.RegisterRequest;
import com.vnpt.authentication.web.rest.response.auth.LoginByGoogleResponse;
import com.vnpt.authentication.web.rest.response.auth.LoginResponse;
import com.vnpt.authentication.web.rest.response.auth.RegisterResponse;
import com.vnpt.authentication.web.rest.response.manage_user.GetDetailUserResponse;
import com.vnpt.common.base.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
public class AuthenticateController {

    @Autowired
    private AuthService authService;

    @Autowired
    private GoogleService googleService;

//    @CheckRoles(enabled = false, action= ACTION.READ)
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest request){
        return authService.login(request);
    }


    @GetMapping("/get-user")
    public ResponseEntity<BaseResponse<GetDetailUserResponse>> getUserByToken(HttpServletRequest request){
        return authService.getUserByToken(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse<LoginResponse>> refreshToken(HttpServletRequest request){
        return authService.refreshToken(request);
    }


    @PostMapping("/register")
    public ResponseEntity<BaseResponse<RegisterResponse>> register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }


    @GetMapping("/get-user-principal")
    public ResponseEntity<UserPrincipal> getUserPrincipal(HttpServletRequest request){
        return authService.getUserPrincipal(request);
    }

    @GetMapping("/login-with-google")
    public ResponseEntity<BaseResponse<LoginByGoogleResponse>> loginWithGoogle(HttpServletRequest request) throws IOException {
        return googleService.loginWithGoogle(request);
    }
}
