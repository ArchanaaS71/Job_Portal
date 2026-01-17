
package com.ey.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ey.security.JwtAuthenticationFilter;
import com.ey.security.JwtAuthorizationFilter;
import com.ey.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource datasource) {
        JdbcDaoImpl jdbc = new JdbcDaoImpl();
        jdbc.setDataSource(datasource);
        jdbc.setUsersByUsernameQuery(
            "select mail as username, password, case when status='ACTIVE' then 1 else 0 end as enabled from users where mail=?"
        );
        jdbc.setAuthoritiesByUsernameQuery(
            "select mail as username, concat('ROLE_', role) as authority from users where mail=?"
        );
        return jdbc;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager am,
            UserDetailsService uds) throws Exception {

        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(am, jwtUtil);
        jwtAuthFilter.setFilterProcessesUrl("/login");

        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers( "/auth/**", "/oauth2/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/consultant/**").hasRole("CONSULTANT")
                .requestMatchers("/jobseeker/**").hasRole("JOB_SEEKER")
                .requestMatchers("/user/**").hasAnyRole("CONSULTANT", "ADMIN", "JOB_SEEKER")
                .anyRequest().authenticated()
        );
        http.addFilter(jwtAuthFilter);
        http.addFilterBefore(new JwtAuthorizationFilter(jwtUtil, uds), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
