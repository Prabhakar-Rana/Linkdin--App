package com.LinkedIn.user_service.repository;

import com.LinkedIn.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByEmail(String email);
}
