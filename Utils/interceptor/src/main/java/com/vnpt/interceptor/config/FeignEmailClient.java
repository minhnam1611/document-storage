package com.vnpt.interceptor.config;

import com.vnpt.interceptor.dto.EmailLogRequest;
import feign.Headers;
import feign.RequestLine;

public interface FeignEmailClient {
    @RequestLine("POST /api/email-logs")
    @Headers("Content-Type: application/json")
    void sendEmailLog(EmailLogRequest request);

}
