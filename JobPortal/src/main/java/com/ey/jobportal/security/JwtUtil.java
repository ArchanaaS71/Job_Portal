package com.ey.jobportal.security;
 
import java.util.Date;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
public class JwtUtil {
 
    private static final String SECRET_KEY = "jobportal_secret_key_123";
    private static final long EXPIRATION_TIME = 86400000;
 
    private JwtUtil() {}
 
    public static String generateToken(String email, String role) {
 
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
 
    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
 
    public static String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }
 
    public static String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
}
}
 
