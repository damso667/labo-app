package com.example.ProjetApiBts.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {
    private SecurityUtil(){}
    public static String currentUsername(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (authentication != null ? authentication.getName():null);
        }

}
