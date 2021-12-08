package com.thoughtworks.mall.infrastructure.security.common;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest(value = SecurityCommonProvider.class)
class SecurityCommonProviderTest {
   SecurityCommonProvider securityCommonProvider = new SecurityCommonProvider();

   @Test
   @WithMockUserImpl
   void should_get_current_user_id_when_security_have_user_info() {
      assertEquals(1L, securityCommonProvider.getCurrentUserId());
   }

   @Test
   @WithMockUser
   void should_throw_exception_when_user_info_is_not_user_detail_dto() {
      assertThrows(GenericBizException.class, () -> securityCommonProvider.getCurrentUserId(), "not exist user details impl dto");
   }

   @Test
   void should_throw_null_exception_when_not_exist_security_context() {
      assertThrows(NullPointerException.class, () -> securityCommonProvider.getCurrentUserId());
   }
}
