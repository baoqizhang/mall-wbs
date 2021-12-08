package com.thoughtworks.mall.user.api.assembler;

import com.thoughtworks.mall.user.api.request.UserAddressRequest;
import com.thoughtworks.mall.user.common.MockUserAddress;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAddressAssemblerTest implements MockUserAddress {
   @Test
   void should_transform_to_user_address_success_when_request_right(){
      var userAddressRequest = new UserAddressRequest();
      userAddressRequest.setAddress(USER_ADDRESS.getAddress());
      userAddressRequest.setName(USER_ADDRESS.getName());
      userAddressRequest.setPhone(USER_ADDRESS.getPhone());
      var userAddress = UserAddressAssembler.toResource(userAddressRequest);

      assertEquals(USER_ADDRESS.getAddress(), userAddress.getAddress());
      assertEquals(USER_ADDRESS.getName(), userAddress.getName());
      assertEquals(USER_ADDRESS.getPhone(), userAddress.getPhone());
   }

   @Test
   void should_transform_to_user_address_response_success_when_request_right(){
      var userAddressList = UserAddressAssembler.toResource(List.of(USER_ADDRESS));
      var userAddress = UserAddressAssembler.toResource(List.of(USER_ADDRESS)).get(0);

      assertEquals(List.of(USER_ADDRESS).size(), userAddressList.size());
      assertEquals(USER_ADDRESS.getName(), userAddress.getName());
      assertEquals(USER_ADDRESS.getPhone(), userAddress.getPhone());
   }
}
