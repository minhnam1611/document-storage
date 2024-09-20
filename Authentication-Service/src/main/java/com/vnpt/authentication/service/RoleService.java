package com.vnpt.authentication.service;


import com.vnpt.authentication.entity.RoleEntity;
import com.vnpt.authentication.entity.RoleResourceRelEntity;
import com.vnpt.authentication.entity.UserRoleRelEntity;
import com.vnpt.authentication.repository.RoleRepository;
import com.vnpt.authentication.repository.RoleResourceRepository;
import com.vnpt.authentication.repository.UserRoleRelRepository;
import com.vnpt.authentication.web.rest.dto.ResourceActionInfo;
import com.vnpt.authentication.web.rest.dto.ResourceInfo;
import com.vnpt.authentication.web.rest.request.manage_role.CreateRoleRequest;
import com.vnpt.authentication.web.rest.request.manage_role.UpdateRoleRequest;
import com.vnpt.authentication.web.rest.response.manage_role.GetDetailRoleResponse;
import com.vnpt.authentication.web.rest.response.manage_role.GetListRoleResponse;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.base.PagingRequest;
import com.vnpt.common.constants.Constant;
import com.vnpt.common.constants.ErrorCodes;
import com.vnpt.common.constants.UserConstant;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleResourceRepository roleResourceRepository;

    private final UserRoleRelRepository userRoleRelRepository;


    public RoleService(RoleRepository roleRepository,RoleResourceRepository roleResourceRepository, UserRoleRelRepository userRoleRelRepository ) {
        this.roleRepository = roleRepository;
        this.roleResourceRepository = roleResourceRepository;
        this.userRoleRelRepository = userRoleRelRepository;
    }

    public ResponseEntity<BaseResponse<GetListRoleResponse>> getListRole(PagingRequest request, Pageable pageable) {
        GetListRoleResponse data = new GetListRoleResponse();
        String keySearch = "";
        if (request.getFilter() == null || request.getFilter().isEmpty()) {
            keySearch = null;
        } else {
            keySearch = "%" + request.getFilter() + "%";
        }
        Page<RoleEntity> listRole = roleRepository.getListRole(keySearch, pageable);
        data.setData(listRole.getContent());
        data.setSizeOfPage(listRole.getSize());
        data.setRecordsTotal(listRole.getTotalElements());
        data.setRecordsFiltered((int) listRole.getTotalElements());
        data.setTotalPages(listRole.getTotalPages());
        data.setData(listRole.getContent());

        BaseResponse<GetListRoleResponse> response = new BaseResponse<>();
        response.success(data);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<GetDetailRoleResponse>> getRoleDetail(String id) {
        GetDetailRoleResponse data = new GetDetailRoleResponse();
        Optional<RoleEntity> roleInfo = roleRepository.findById(id);
        if (roleInfo.isPresent()) {
            data.setRoleInfo(roleInfo.get());
            List<Object[]> listSrc = roleRepository.getRrcOfRole(id);
            if (listSrc.size() > 0) {
                List<ResourceInfo> srcInfo = new ArrayList<>();

                for (Object[] obj : listSrc) {
                    ResourceInfo resourceInfo = new ResourceInfo();
                    resourceInfo.setRoleCode((String) obj[0]);
                    resourceInfo.setRoleName((String) obj[1]);
                    resourceInfo.setSrcCode((String) obj[2]);
                    resourceInfo.setSrcName((String) obj[3]);
                    resourceInfo.setSrcPath((String) obj[5]);
                    if (obj[4] != null) {
                        List<String> lstAction = List.of(String.valueOf(obj[4]).split(","));
                        resourceInfo.setAction(lstAction);

                    }

                    srcInfo.add(resourceInfo);
                }

                data.setSrcInfo(srcInfo);
            }
        }

        BaseResponse<GetDetailRoleResponse> response = new BaseResponse<>();
        response.success(data);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse<String>> deleteRole(String id){
        BaseResponse<String> response = new BaseResponse<>();
        Optional<RoleEntity> roleOpt = roleRepository.findById(id);
        if(roleOpt.isPresent()){
            RoleEntity role = roleOpt.get();
            List<UserRoleRelEntity> lstUser = userRoleRelRepository.findByRoleId(role.getId());
            if(!lstUser.isEmpty()){
                response.error(ErrorCodes.ROLE_HAS_USED);
                return ResponseEntity.ok(response);
            }
            role.setStatus(Constant.STATUS_INACTIVE);
            roleRepository.save(role);
            response.success("Delete role success.");
        }else{
            response.error(ErrorCodes.NOT_FOUND_ROLE);
        }

        return ResponseEntity.ok(response);

    }

    @Transactional
    public ResponseEntity<BaseResponse<RoleEntity>> createRole(CreateRoleRequest request) {
        BaseResponse<RoleEntity> response = new BaseResponse<>();
        RoleEntity newRole = new RoleEntity();
        newRole.setCode(request.getCode());
        newRole.setName(request.getName());
        newRole.setDesc(request.getDesc());
        newRole.setStatus(Constant.STATUS_DEFAULT);
        newRole.setCreatedBy(Constant.SYSTEM);

        RoleEntity data = roleRepository.save(newRole);


        List<ResourceActionInfo> srcList = request.getSrcList();

        srcList.forEach(src -> {
            RoleResourceRelEntity rr = new RoleResourceRelEntity();
            rr.setRoleId(data.getId());
            rr.setResourceId(src.getSrcCode());
            rr.setAction(String.join(",", src.getLstAction()));
            rr.setCreatedBy(UserConstant.SYSTEM); // TODO
            roleResourceRepository.save(rr);
        });
        response.success(data);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<BaseResponse<RoleEntity>> updateRole(UpdateRoleRequest request){
        BaseResponse<RoleEntity> response = new BaseResponse<>();

        Optional<RoleEntity> oldRoleOpt = roleRepository.findById(request.getId());
        if(oldRoleOpt.isEmpty()){
            response.setCode(ErrorCodes.NOT_FOUND_ROLE.code);
            response.setCode(ErrorCodes.NOT_FOUND_ROLE.message);
        }else{
            RoleEntity oldRole = oldRoleOpt.get();
            oldRole.setCode(request.getCode());
            oldRole.setStatus(request.getStatus());
            oldRole.setName(request.getName());
            oldRole.setDesc(request.getDesc());
            RoleEntity data = roleRepository.save(oldRole);

            //oldRoleResource
            List<RoleResourceRelEntity> listOldRR = roleResourceRepository.findByRoleId(request.getId());
            if(!listOldRR.isEmpty()){
                roleResourceRepository.deleteAllInBatch(listOldRR);
            }

            //newRoleResource
            List<ResourceActionInfo> srcInfo = request.getSrcList();
            srcInfo.forEach((src) -> {
                RoleResourceRelEntity roleResourceRelEntity = new RoleResourceRelEntity();
                roleResourceRelEntity.setResourceId(src.getSrcCode());
                roleResourceRelEntity.setRoleId(request.getId());
                roleResourceRelEntity.setCreatedBy(Constant.SYSTEM); //TODO
                roleResourceRelEntity.setAction(String.join(",", src.getLstAction()));
                roleResourceRepository.save(roleResourceRelEntity);
            });

            response.success(data);
        }

        return ResponseEntity.ok(response);
    }
}
