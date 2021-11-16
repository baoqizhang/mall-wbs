package com.thoughtworks.mall.user.infrastructure.repository;

import com.thoughtworks.mall.user.domain.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
   List<UserAddress> findAllByUserId(Long userId);
}
