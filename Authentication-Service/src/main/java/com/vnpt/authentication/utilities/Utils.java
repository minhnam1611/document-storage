package com.vnpt.authentication.utilities;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Utils {

    public static String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public static String genOTPValue() {
        String OTP_CHARACTERS = "0123456789";
        int OTP_LENGTH = 6;

        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        Random random = new Random();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = random.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(index));
        }

        return otp.toString();
    }
    public static long getDateDiff(Date startDate, Date endDate, TimeUnit timeUnit) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }


}
