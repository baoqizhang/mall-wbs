package com.thoughtworks.mall.user.application;

import com.thoughtworks.mall.infrastructure.security.common.SecurityCommonProvider;
import com.thoughtworks.mall.user.common.MockUserAddress;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAddressApplicationServiceTest implements MockUserAddress {
   SecurityCommonProvider securityCommonProvider = mock(SecurityCommonProvider.class);
   UserAddressService userAddressService = mock(UserAddressService.class);
   UserAddressApplicationService userAddressApplicationService = new UserAddressApplicationService(userAddressService, securityCommonProvider);

   @Test
   void should_get_current_user_address_when_user_login() {
      when(securityCommonProvider.getCurrentUserId()).thenReturn(1L);
      when(userAddressService.findAllByUserId(1L)).thenReturn(List.of(USER_ADDRESS));

      assertEquals(List.of(USER_ADDRESS), userAddressApplicationService.getCurrentUserAddress());
   }

   @Test
   void should_success_create_current_user_address_when_user_login() {
      when(securityCommonProvider.getCurrentUserId()).thenReturn(1L);

      assertDoesNotThrow(() -> userAddressApplicationService.createCurrentUserAddress(USER_ADDRESS));
   }

}
