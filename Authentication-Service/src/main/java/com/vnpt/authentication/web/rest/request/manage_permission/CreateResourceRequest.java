package com.vnpt.authentication.web.rest.request.manage_permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResourceRequest {
    private String name;

    private String code;

    private String path;

    private String desc;
}
