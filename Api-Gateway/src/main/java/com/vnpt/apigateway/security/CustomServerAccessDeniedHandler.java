package com.vnpt.apigateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.constants.ErrorCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class CustomServerAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] resultAsByteArr;
        try {
            String result =
                    objectMapper.writeValueAsString(BaseResponse.builder()
                            .code(ErrorCodes.FORBIDDEN.code)
                            .desc(ErrorCodes.FORBIDDEN.message).build());
            resultAsByteArr = result.getBytes(StandardCharsets.UTF_8);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response.writeWith(
                Mono.just(
                        response.bufferFactory().allocateBuffer(resultAsByteArr.length).write(resultAsByteArr)
                )
        );
    }
}
