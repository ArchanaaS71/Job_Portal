package com.ey.jobportal.security;
import java.io.IOException;
import java.util.Collections;
 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
 
public class JwtAuthorizationFilter extends OncePerRequestFilter {
 
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/auth");
    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
 
        String header = request.getHeader("Authorization");
 
        if (header != null && header.startsWith("Bearer ")) {
 
            String token = header.substring(7);
            String email = JwtUtil.extractEmail(token);
            String role = JwtUtil.extractRole(token);
 
            var authority = new SimpleGrantedAuthority("ROLE_" + role);
 
            var authToken = new UsernamePasswordAuthenticationToken(
                    email, null, Collections.singleton(authority));
 
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
 
        filterChain.doFilter(request, response);
    }
}