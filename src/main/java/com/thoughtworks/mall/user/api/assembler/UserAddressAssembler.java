package com.thoughtworks.mall.user.api.assembler;

import com.thoughtworks.mall.user.api.response.UserAddressResponse;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import org.springframework.stereotype.Component;

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
}
