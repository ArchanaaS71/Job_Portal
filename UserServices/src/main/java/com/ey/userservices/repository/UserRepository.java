package com.ey.userservices.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.userservices.dto.response.UserResponse;
import com.ey.userservices.entity.User;
import com.ey.userservices.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(Role role);
	Optional<User> findByEmail(String email);

}
