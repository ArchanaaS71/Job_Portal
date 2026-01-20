package com.ey.jobportal.security.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.repository.RoleRepository;

@Component
public class RoleInitializer {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {

        for (RoleName roleName : RoleName.values()) {

            roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleName);
                    role.setDescription(roleName.name() + " role");
                    return roleRepository.save(role);
                });
        }
    }
}
