package com.ey.jobportal.security.config;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
public class CurrentUserUtil {
 
    private CurrentUserUtil() {}
 
    public static String getCurrentUserEmail() {
 
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
 
        return auth != null ? auth.getName() : null;
    }
 
    public static String getCurrentUserRole() {
 
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
 
        return auth.getAuthorities().iterator().next().getAuthority();
    }
}