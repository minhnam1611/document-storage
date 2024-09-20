package com.vnpt.interceptor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal {

    private String username;

    private String email;

    private String phone;

    private TenantInfo tenantInfo;

    private List<String> roleInfo;

    private List<PrincipalInfo> principalInfo;

}
