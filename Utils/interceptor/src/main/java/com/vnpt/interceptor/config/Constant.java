package com.vnpt.interceptor.config;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final Map<String, String> mapAction = new HashMap<>() {{
        put("1", "GET");
        put("2", "POST");
        put("3", "PUT");
        put("4", "DELETE");
    }};
}
