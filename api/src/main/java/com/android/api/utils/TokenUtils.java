package com.android.api.utils;

import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtils {
    public static String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;

    }
}
