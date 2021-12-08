package com.thoughtworks.mall.user.domain.service;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.security.common.SecurityCommonProvider;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import com.thoughtworks.mall.user.infrastructure.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

   private final UserAddressRepository userAddressRepository;

   public List<UserAddress> getCurrentUserAddress() {
      var currentUserId = SecurityCommonProvider.getCurrentUserId();
      return userAddressRepository.findAllByUserId(currentUserId);
   }

   public void createCurrentUserAddress(UserAddress userAddress) {
      userAddress.updateUserId(SecurityCommonProvider.getCurrentUserId());
      userAddressRepository.save(userAddress);
   }

   public UserAddress findCurrentUserAddressById(Long addressId) {
      return userAddressRepository.findByUserIdAndId(SecurityCommonProvider.getCurrentUserId(), addressId)
         .orElseThrow(() -> new GenericBizException("current user this address."));
   }
}
