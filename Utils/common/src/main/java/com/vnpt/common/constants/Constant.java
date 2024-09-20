package com.vnpt.common.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static final String SYSTEM = "system";
    public static final String USER = "user";

    public static final String STATUS_ACTIVE = "ACTIVE";

    public static final String STATUS_BLOCK = "BLOCK";

    public static final String STATUS_INACTIVE = "INACTIVE";

    public static final String STATUS_DEFAULT = "ACTIVE";

    public static final String ROLE_DEFAULT = "USER";

    public static final String ROLE_ADMIN_SUPREME = "ADMIN SUPREME";

    //Service OTP
    public static final String SRV_REGISTER = "SRV_REGISTER";

    public static final String SRV_FORGET_PASSWORD = "SRV_FORGET_PASSWORD";

    public static final Map<String, String> mapAction = new HashMap<>() {{
        put("1", "CREATE");
        put("2", "VIEW");
        put("3", "UPDATE");
        put("4", "DELETE");
    }};

    //LAYOUT DEFINE
    public static final String LAYOUT_DEFAULT = "default_template";
    public static final String LAYOUT_CUSTOM = "custom_template";

    // Define MIME types for different categories
    public static final List<String> IMAGE_MIME_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
    public static final List<String> VIDEO_MIME_TYPES = Arrays.asList("video/mp4", "video/mpeg", "video/quicktime");
    public static final List<String> AUDIO_MIME_TYPES = Arrays.asList("audio/mpeg", "audio/ogg", "audio/wav");
    public static final List<String> PDF_MIME_TYPES = Arrays.asList("application/pdf");
    public static final List<String> DOCUMENT_MIME_TYPES = Arrays.asList("application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

}
