package com.thoughtworks.mall.user.infrastructure.repository;

import com.thoughtworks.mall.user.domain.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
   List<UserAddress> findAllByUserId(Long userId);

   Optional<UserAddress> findByUserIdAndId(@Param("userId") Long userId, @Param("id") Long id);
}
