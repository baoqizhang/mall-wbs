package com.thoughtworks.mall.user.infrastructure.repository;

import com.thoughtworks.mall.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity> findByUsername(String username);
}
