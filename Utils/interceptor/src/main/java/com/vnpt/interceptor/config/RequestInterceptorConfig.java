package com.vnpt.interceptor.config;

import com.vnpt.interceptor.PermissionInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RequestInterceptorConfig implements WebMvcConfigurer {

    private PermissionInterceptor permissionInterceptor;

    public RequestInterceptorConfig(PermissionInterceptor permissionInterceptor) {
        super();
        this.permissionInterceptor = permissionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor);
    }
}
