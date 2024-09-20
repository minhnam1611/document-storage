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
public class RolePrincipal {


    private Long id;

    private String name;

    private String code;

//    private RoleStatus status;

    private String description;

    private List<ResourcePrincipal> grants;
}
