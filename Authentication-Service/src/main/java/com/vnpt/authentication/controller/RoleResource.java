package com.vnpt.authentication.controller;

import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.service.RoleService;
import com.vnpt.authentication.web.rest.request.manage_role.CreateRoleRequest;
import com.vnpt.authentication.web.rest.request.manage_role.UpdateRoleRequest;
import com.vnpt.authentication.web.rest.response.manage_role.GetDetailRoleResponse;
import com.vnpt.authentication.web.rest.response.manage_role.GetListRoleResponse;
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
@RequestMapping("/api/role")
public class RoleResource {

    private final int PAGE_SIZE = 10;

    @Autowired
    private RoleService roleService;

    @CheckRoles(enabled = true, action= ACTION.READ)
    @PostMapping("/roles-list")
    public ResponseEntity<BaseResponse<GetListRoleResponse>> getAllRoles(@RequestBody PagingRequest request) {
        Pageable pageable = PageRequest.of(request.getCurrentPage(), PAGE_SIZE);
        return roleService.getListRole(request, pageable);
    }

    @CheckRoles(enabled = true, action= ACTION.READ)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetDetailRoleResponse>> getDetailRole(@PathVariable String id) {
        return roleService.getRoleDetail(id);
    }

    @CheckRoles(enabled = true, action= ACTION.CREATE)
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<RoleEntity>> createRole(@RequestBody CreateRoleRequest request){
        return roleService.createRole(request);
    }

    @CheckRoles(enabled = true, action= ACTION.UPDATE)
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<RoleEntity>> updateRole(@RequestBody UpdateRoleRequest request){
        return roleService.updateRole(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteRole(@PathVariable String id) {
        return roleService.deleteRole(id);
    }
}
