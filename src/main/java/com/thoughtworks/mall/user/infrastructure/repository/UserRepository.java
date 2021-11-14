package com.thoughtworks.mall.user.infrastructure.repository;

import com.thoughtworks.mall.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
