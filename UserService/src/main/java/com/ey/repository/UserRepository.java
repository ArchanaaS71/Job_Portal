package com.ey.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(Role role);
	Optional<User> findByEmail(String email);

}
