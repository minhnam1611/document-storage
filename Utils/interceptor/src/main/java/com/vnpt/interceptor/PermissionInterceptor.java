package com.vnpt.interceptor;

import com.vnpt.interceptor.config.CheckRoles;
import com.vnpt.interceptor.config.Constant;
import com.vnpt.interceptor.config.FeignUserClient;
import com.vnpt.interceptor.dto.AccountDetail;
import com.vnpt.interceptor.dto.PrincipalInfo;
import com.vnpt.interceptor.dto.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;


public class PermissionInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
    private final FeignUserClient feignUserClient;

    @Value("${vnpt.app.jwtExpirationMs}")
    private long JWT_EXPIRATION;
    private final RedisTemplate<String, Object> redisTemplate;

    public PermissionInterceptor(FeignUserClient feignUserClient, RedisTemplate<String, Object> redisTemplate) {
        this.feignUserClient = feignUserClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Method method = ((HandlerMethod) handler).getMethod();
            if (method.isAnnotationPresent(CheckRoles.class)) {
                Annotation annotation = method.getAnnotation(CheckRoles.class);
                CheckRoles checkRoles = (CheckRoles) annotation;
                if (!checkRoles.enabled())
                {
                    return true;
                }else{
                    String action = request.getMethod(); //get method ==> get ACTION
                    if(checkRoles.action() != null){
                        action =  Constant.mapAction.get(checkRoles.action().getAction());
                    }


                    String path = request.getRequestURI(); //get url ==> get RESOURCE

                    String resource = "";
                    if (path != null && path.length() > 1) {
                        String[] pathSegments = path.split("/");
                        if (pathSegments.length > 1) {
                            resource = "/" + pathSegments[2];
                        }
                    }

                    String token = request.getHeader("Authorization");
                    if(token == null || token.isEmpty()){
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        return false;
                    }
                    UserPrincipal user = (UserPrincipal) getFromRedis(token);
                    if(user == null){
                        user = feignUserClient.getUser(token.replace("Bearer ", ""));
                        if(user == null) {
                            return false;
                        }
                        addToRedis(token, user, Duration.ofMillis(JWT_EXPIRATION));
                    }

                    AccountDetail accountDetail = new AccountDetail();
                    accountDetail.setUserInfo(user);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(accountDetail, null, accountDetail.getAuthorities());

                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authentication);

                    SecurityContextHolder.setContext(securityContext);

                    //Check Roles
                    List<PrincipalInfo> principalInfoList = user.getPrincipalInfo();
                    for(PrincipalInfo principalInfo : principalInfoList){
                        if(principalInfo.getPath().equals(resource)){
                            List<String> listAction =List.of(principalInfo.getAction().split(","));
                            for (String act : listAction){
                                if(Constant.mapAction.get(act).equals(action)){
                                    return true;
                                }
                            }
                        }
                    }

                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return false;

                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //* Business logic just before the response reaches the client and the request is served
        try {
            System.out.println("2 - postHandle() : After the Controller serves the request (before returning back response to the client)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToRedis(String key, Object value, Duration expiration) {
        redisTemplate.opsForValue().set(key, value, expiration);
    }

    public Object getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
