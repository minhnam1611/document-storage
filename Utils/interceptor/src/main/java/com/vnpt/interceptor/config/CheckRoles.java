package com.vnpt.interceptor.config;


import com.vnpt.interceptor.permission.ACTION;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRoles {
    boolean enabled() default false;
    ACTION action();
}
