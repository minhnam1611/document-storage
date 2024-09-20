package com.vnpt.authentication.service;
import com.vnpt.authentication.entity.ResourceEntity;
import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.entity.RoleResourceRelEntity;
import com.vnpt.authentication.entity.UserRoleRelEntity;
import com.vnpt.authentication.repository.ResourceRepository;
import com.vnpt.authentication.repository.RoleResourceRepository;
import com.vnpt.authentication.web.rest.request.manage_permission.CreateResourceRequest;
import com.vnpt.authentication.web.rest.request.manage_permission.UpdateResourceRequest;
import com.vnpt.authentication.web.rest.response.manage_permission.GetListResourceResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.base.PagingRequest;
import com.vnpt.common.constants.Constant;
import com.vnpt.common.constants.ErrorCodes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    public ResponseEntity<BaseResponse<GetListResourceResponse>> getLisRrc(PagingRequest request, Pageable pageable) {
        GetListResourceResponse data = new GetListResourceResponse();
        String keySearch = "";
        if (request.getFilter() == null || request.getFilter().isEmpty()) {
            keySearch = null;
        } else {
            keySearch = "%" + request.getFilter() + "%";
        }
        Page<ResourceEntity> listSrc = resourceRepository.getListSrc(keySearch, pageable);
        data.setData(listSrc.getContent());
        data.setSizeOfPage(listSrc.getSize());
        data.setRecordsTotal(listSrc.getTotalElements());
        data.setRecordsFiltered((int) listSrc.getTotalElements());
        data.setTotalPages(listSrc.getTotalPages());
        data.setData(listSrc.getContent());

        BaseResponse<GetListResourceResponse> response = new BaseResponse<>();
        response.success(data);

        return ResponseEntity.ok(response);
    }
    public ResponseEntity<BaseResponse<ResourceEntity>> getDetail( String id){
        Optional<ResourceEntity> dataOpt = resourceRepository.findById(id);
        BaseResponse<ResourceEntity> response = new BaseResponse<>();
        if(dataOpt.isPresent()){
            ResourceEntity data = dataOpt.get();
            response.success(data);
        }else{
            response.setCode(ErrorCodes.NOT_FOUND_RESOURCE.code);
            response.setCode(ErrorCodes.NOT_FOUND_RESOURCE.message);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<ResourceEntity>> createResource(CreateResourceRequest request){
        BaseResponse<ResourceEntity> response = new BaseResponse<>();
        ResourceEntity newResource = new ResourceEntity();
        newResource.setCode(request.getCode());
        newResource.setName(request.getName());
        newResource.setPath(request.getPath());
        newResource.setDesc(request.getDesc());
        ResourceEntity data = resourceRepository.save(newResource);
        response.success(data);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<ResourceEntity>> updateResource(UpdateResourceRequest request){
        BaseResponse<ResourceEntity> response = new BaseResponse<>();

        Optional<ResourceEntity> resourceOpt = resourceRepository.findById(request.getId());
        if(resourceOpt.isEmpty()){
            response.error(ErrorCodes.NOT_FOUND);
            return ResponseEntity.ok(response);
        }
        ResourceEntity resource = resourceOpt.get();
        resource.setCode(request.getCode());
        resource.setPath(request.getPath());
        resource.setName(request.getName());
        resource.setDesc(request.getDesc());
        ResourceEntity data = resourceRepository.save(resource);
        response.success(data);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<BaseResponse<String>> deleteResource(String id){
        BaseResponse<String> response = new BaseResponse<>();
        Optional<ResourceEntity> srcOpt = resourceRepository.findById(id);
        if(srcOpt.isPresent()){
            ResourceEntity src = srcOpt.get();
            List<RoleResourceRelEntity> roleResourceRel = roleResourceRepository.findByResourceId(src.getId());
            roleResourceRepository.deleteAll(roleResourceRel);
            resourceRepository.delete(src);
            response.success("Delete role success.");
        }else{
            response.error(ErrorCodes.NOT_FOUND_ROLE);
        }

        return ResponseEntity.ok(response);

    }
}
