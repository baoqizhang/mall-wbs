package com.thoughtworks.mall.user.application;

import com.thoughtworks.mall.infrastructure.security.common.SecurityCommonProvider;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressApplicationService {
   private final UserAddressService userAddressService;
   private final SecurityCommonProvider securityCommonProvider;

   public List<UserAddress> getCurrentUserAddress() {
      var currentUserId = securityCommonProvider.getCurrentUserId();
      return userAddressService.findAllByUserId(currentUserId);
   }

   public void createCurrentUserAddress(UserAddress userAddress) {
      userAddress.updateUserId(securityCommonProvider.getCurrentUserId());
      userAddressService.create(userAddress);
   }
}
