package com.vnpt.interceptor.config;

import com.vnpt.interceptor.dto.UserPrincipal;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FeignUserClient {
    @RequestLine("GET /api/auth/get-user-principal")
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer {accessToken}"
    })
    UserPrincipal getUser(@Param("accessToken") String accessToken);
}
