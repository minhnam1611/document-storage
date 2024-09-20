package com.vnpt.authentication.web.rest.request.manage_permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResourceRequest extends CreateResourceRequest{
    private String id;
}
