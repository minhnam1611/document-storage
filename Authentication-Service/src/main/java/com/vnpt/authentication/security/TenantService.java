package com.vnpt.authentication.security;

import com.vnpt.authentication.entity.TenantEntity;
import com.vnpt.authentication.repository.TenantRepositoty;
import com.vnpt.authentication.web.rest.request.tenant.AddTenantRequest;
import com.vnpt.authentication.web.rest.request.tenant.UpdateTenantRequest;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.base.PagingRequest;
import com.vnpt.common.base.PagingResponse;
import com.vnpt.common.constants.ErrorCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {



    @Autowired
    private TenantRepositoty tenantRepositoty;

    public ResponseEntity<BaseResponse<String>> addTenant(@RequestBody AddTenantRequest request){
        BaseResponse<String> response = new BaseResponse<>();
        if(checkNameExisted(request.getName())){
            response.error(ErrorCodes.TENANT_NAME_EXISTED);
            return ResponseEntity.ok(response);
        }
        if(checkTopicExisted(request.getTopic())){
            response.error(ErrorCodes.TENANT_TOPIC_EXISTED);
            return ResponseEntity.ok(response);
        }

        TenantEntity newTenant = new TenantEntity();
        newTenant.setName(request.getName());
        newTenant.setTopic(request.getTopic());

        tenantRepositoty.save(newTenant);
        response.success("Create Tenant Success.");

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<PagingResponse<List<TenantEntity>>> searchTenant( PagingRequest request, Pageable pageable){
        PagingResponse<List<TenantEntity>> response = new PagingResponse<>();
        String keySearch = "";
        if (request.getFilter() == null || request.getFilter().isEmpty()) {
            keySearch = null;
        } else {
            keySearch = "%" + request.getFilter() + "%";
        }

        Page<TenantEntity> tenantEntityPage = tenantRepositoty.getListTenant(keySearch,pageable);
        response.setRecordsTotal(tenantEntityPage.getTotalElements());
        response.setSizeOfPage(tenantEntityPage.getSize());
        response.setRecordsFiltered(tenantEntityPage.getContent().size());
        response.setTotalPages(tenantEntityPage.getTotalPages());
        response.success(tenantEntityPage.getContent());

        return ResponseEntity.ok(response);

    }


    public ResponseEntity<BaseResponse<TenantEntity>> updateTenant(UpdateTenantRequest request){
        BaseResponse<TenantEntity> response = new BaseResponse<>();
        Optional<TenantEntity> tenantOpt = tenantRepositoty.findById(request.getId());
        if(tenantOpt.isEmpty()){
            response.error(ErrorCodes.NOT_FOUND);
            return ResponseEntity.ok(response);
        }
        TenantEntity oldTenant = tenantOpt.get();
        oldTenant.setName(request.getName());
//        oldTenant.setTopic(request.getTopic());

        TenantEntity tenantUpdated = tenantRepositoty.save(oldTenant);
        response.success(tenantUpdated);

        return ResponseEntity.ok(response);
    }

    private boolean checkTopicExisted(String topic){
        TenantEntity tenant = tenantRepositoty.findByTopic(topic);
        return tenant != null;
    }
    private boolean checkNameExisted(String name){
        TenantEntity tenant = tenantRepositoty.findByName(name);
        return tenant != null;
    }
}
