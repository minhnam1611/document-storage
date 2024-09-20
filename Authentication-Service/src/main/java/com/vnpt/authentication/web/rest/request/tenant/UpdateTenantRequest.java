package com.vnpt.authentication.web.rest.request.tenant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTenantRequest extends AddTenantRequest{
    private String id;
}
