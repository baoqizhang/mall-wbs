package com.thoughtworks.mall.user.domain.service;

import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.user.common.MockUserAddress;
import com.thoughtworks.mall.user.infrastructure.repository.UserAddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(value = UserAddressRepository.class)
class UserAddressServiceTest implements MockUserAddress {

   UserAddressRepository userAddressRepository = mock(UserAddressRepository.class);
   UserAddressService userAddressService = new UserAddressService(userAddressRepository);

   @Test
   @WithMockUserImpl
   void should_get_address_when_user_login() {
      when(userAddressRepository.findAllByUserId(1L)).thenReturn(List.of(USER_ADDRESS));
      assertEquals(List.of(USER_ADDRESS), userAddressService.getCurrentUserAddress());
   }
}
