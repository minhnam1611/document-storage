package com.vnpt.apigateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpt.common.base.BaseResponse;
import com.vnpt.common.constants.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthEntryPointJwt implements ServerAuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException)
//            throws IOException, ServletException {
//        logger.error("Unauthorized error: {}", authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
//    }


    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        logger.error("Unauthorized error: {}", ex.getMessage());

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] resultAsByteArr;
        try {
            String result =
                    objectMapper.writeValueAsString(BaseResponse.builder()
                            .code(ErrorCodes.UNAUTHORIZED.code)
                            .desc(ErrorCodes.UNAUTHORIZED.message).build());
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
