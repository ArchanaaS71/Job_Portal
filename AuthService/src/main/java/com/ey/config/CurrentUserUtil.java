
package com.ey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.ey.entity.User;
import com.ey.repository.UserRepository;

@Component
public class CurrentUserUtil {

    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        return userRepository.findByMail(auth.getName()).orElse(null);
    }
}

