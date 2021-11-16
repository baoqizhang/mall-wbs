package com.thoughtworks.mall.user.infrastructure.repository;

import com.thoughtworks.mall.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);
}
