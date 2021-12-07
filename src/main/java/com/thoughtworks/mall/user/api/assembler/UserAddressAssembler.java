package com.thoughtworks.mall.user.api.assembler;

import com.thoughtworks.mall.user.api.request.UserAddressRequest;
import com.thoughtworks.mall.user.api.response.UserAddressResponse;
import com.thoughtworks.mall.user.domain.entity.UserAddress;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class UserAddressAssembler {
   public static List<UserAddressResponse> toResource(List<UserAddress> userAddressList) {
      return userAddressList.stream().map(address -> new UserAddressResponse()
            .setId(address.getId())
            .setName(address.getName())
            .setAddress(address.getAddress())
            .setPhone(address.getPhone()))
         .collect(Collectors.toList());
   }

   public static UserAddress toResource(UserAddressRequest userAddressRequest) {
      var userAddress = UserAddress.builder()
         .address(userAddressRequest.getAddress())
         .name(userAddressRequest.getName())
         .prime(false)
         .phone(userAddressRequest.getPhone())
         .build();
      userAddress.updateBasicInfo(Instant.now(), Instant.now());

      return userAddress;
   }
}
