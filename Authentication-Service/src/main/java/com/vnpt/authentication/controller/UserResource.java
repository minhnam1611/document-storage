package com.vnpt.authentication.controller;


import com.vnpt.authentication.service.UserService;
import com.vnpt.authentication.web.rest.request.manage_user.CreateUserRequest;
import com.vnpt.authentication.web.rest.request.manage_user.UpdateUserRequest;
import com.vnpt.authentication.web.rest.response.manage_user.GetDetailUserResponse;
import com.vnpt.authentication.web.rest.response.manage_user.GetListUserResponse;
import com.vnpt.common.base.BaseResponse;

import com.vnpt.common.base.PagingRequest;
import com.vnpt.interceptor.config.CheckRoles;
import com.vnpt.interceptor.permission.ACTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserResource {

    private final int PAGE_SIZE = 10;

    @Autowired
    private UserService userService;

    @CheckRoles(enabled = true, action= ACTION.READ)
    @PostMapping("/users-list")
    public ResponseEntity<BaseResponse<GetListUserResponse>> getAllUsers(@RequestBody PagingRequest request) {
        Pageable pageable = PageRequest.of(request.getCurrentPage(), PAGE_SIZE );
        return userService.getListUser(request,pageable);
    }

    @CheckRoles(enabled = true, action= ACTION.READ)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetDetailUserResponse>> getDetailUser(@PathVariable String id){
        return userService.getUserDetail(id);
    }


    @CheckRoles(enabled = true, action= ACTION.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @CheckRoles(enabled = true, action= ACTION.CREATE)
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<GetDetailUserResponse>> createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @CheckRoles(enabled = true, action= ACTION.UPDATE)
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<GetDetailUserResponse>> update(@RequestBody UpdateUserRequest request){
        return userService.updateUser(request);
    }
}
