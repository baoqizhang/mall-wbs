package com.thoughtworks.mall.user.domain.service;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import com.thoughtworks.mall.user.infrastructure.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

   private final UserAddressRepository userAddressRepository;

   public List<UserAddress> findAllByUserId(Long userId) {
      return userAddressRepository.findAllByUserId(userId);
   }

   public void create(UserAddress userAddress) {
      userAddressRepository.save(userAddress);
   }

   public UserAddress findByUserIdAndId(Long userId, Long addressId) {
      return userAddressRepository.findByUserIdAndId(userId, addressId)
         .orElseThrow(() -> new GenericBizException("current user this address."));
   }
}
