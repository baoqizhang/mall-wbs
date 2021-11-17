package com.thoughtworks.mall.user.domain.service;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.security.common.SercurityCommonProvider;
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
      var currentUserId = new SercurityCommonProvider().getCurrentUserId();
      return userAddressRepository.findAllByUserId(currentUserId);
   }

   public void createAddress(UserAddress userAddress) {
      userAddressRepository.save(userAddress);
   }

   public UserAddress findById(Long addressId) {
      var currentUserId = new SercurityCommonProvider().getCurrentUserId();

      return userAddressRepository.findByUserIdAndId(currentUserId, addressId)
         .orElseThrow(() -> new GenericBizException("current user this address."));
   }
}
