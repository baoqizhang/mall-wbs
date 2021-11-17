package com.thoughtworks.mall.user.api.assembler;

import com.thoughtworks.mall.infrastructure.security.common.SercurityCommonProvider;
import com.thoughtworks.mall.user.api.request.UserAddressRequest;
import com.thoughtworks.mall.user.api.response.UserAddressResponse;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAddressAssembler {
   public List<UserAddressResponse> toResouce(List<UserAddress> userAddressList) {
      return userAddressList.stream().map(address -> new UserAddressResponse()
            .setId(address.getId())
            .setName(address.getName())
            .setAddress(address.getAddress())
            .setPhone(address.getPhone()))
         .collect(Collectors.toList());
   }

   public UserAddress toResouce(UserAddressRequest userAddressRequest) {
      var userAddress = UserAddress.builder()
         .userId(new SercurityCommonProvider().getCurrentUserId())
         .address(userAddressRequest.getAddress())
         .name(userAddressRequest.getName())
         .prime(false)
         .phone(userAddressRequest.getPhone())
         .build();
      userAddress.updateBasicInfo(Instant.now(), Instant.now());

      return userAddress;
   }
}
