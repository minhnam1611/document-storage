package com.vnpt.authentication.controller;



import com.vnpt.authentication.entity.ResourceEntity;
import com.vnpt.authentication.service.ResourceService;
import com.vnpt.authentication.web.rest.request.manage_permission.CreateResourceRequest;
import com.vnpt.authentication.web.rest.request.manage_permission.UpdateResourceRequest;
import com.vnpt.authentication.web.rest.response.manage_permission.GetListResourceResponse;
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
@RequestMapping("/api/src")
public class PermissionResource {
    private final int PAGE_SIZE = 10;

    @Autowired
    private ResourceService resourceService;

    @CheckRoles(enabled = true, action = ACTION.READ)
    @PostMapping("/src-list")
    public ResponseEntity<BaseResponse<GetListResourceResponse>> getAllRoles(@RequestBody PagingRequest request) {
        Pageable pageable = PageRequest.of(request.getCurrentPage(), PAGE_SIZE);
        return resourceService.getLisRrc(request, pageable);
    }

    @CheckRoles(enabled = true, action = ACTION.READ)
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ResourceEntity>> getDetail(@PathVariable String id){
        return resourceService.getDetail(id);
    }

    @CheckRoles(enabled = true, action = ACTION.CREATE)
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ResourceEntity>> createResource(@RequestBody CreateResourceRequest request){
        return resourceService.createResource(request);
    }

    @CheckRoles(enabled = true, action = ACTION.UPDATE)
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ResourceEntity>> updateResource(@RequestBody UpdateResourceRequest request){
        return resourceService.updateResource(request);
    }

    @CheckRoles(enabled = true, action = ACTION.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> deleteResource(@PathVariable String id) {
        return resourceService.deleteResource(id);
    }
}
