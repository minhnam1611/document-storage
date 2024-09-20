package com.vnpt.authentication.controller;

import com.vnpt.authentication.entity.TenantEntity;
import com.vnpt.authentication.security.TenantService;
import com.vnpt.authentication.web.rest.request.tenant.AddTenantRequest;
import com.vnpt.authentication.web.rest.request.tenant.UpdateTenantRequest;
import com.vnpt.authentication.web.rest.response.manage_user.GetListUserResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.base.PagingRequest;
import com.vnpt.common.base.PagingResponse;
import com.vnpt.interceptor.config.CheckRoles;
import com.vnpt.interceptor.permission.ACTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    private final int PAGE_SIZE = 10;

    @PostMapping("/add-tenant")
    public ResponseEntity<BaseResponse<String>> addTenant(@RequestBody AddTenantRequest request){
        return tenantService.addTenant(request);
    }

    @PostMapping("/update-tenant")
    public ResponseEntity<BaseResponse<TenantEntity>> updateTenant(@RequestBody UpdateTenantRequest request){
        return tenantService.updateTenant(request);
    }

    @PostMapping("/tenant-list")
    public ResponseEntity<PagingResponse<List<TenantEntity>>> searchTenant(@RequestBody PagingRequest request) {
        Pageable pageable = PageRequest.of(request.getCurrentPage(), PAGE_SIZE );
        return tenantService.searchTenant(request,pageable);
    }

}
