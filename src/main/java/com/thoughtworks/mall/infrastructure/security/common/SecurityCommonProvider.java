package com.thoughtworks.mall.infrastructure.security.common;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.user.domain.dto.UserDetailsImplDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityCommonProvider {

   private UserDetailsImplDto getUserDetailsImplDto() {
      var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetailsImplDto) {
         return ((UserDetailsImplDto) principal);
      }
      throw new GenericBizException("not exist user details impl dto");
   }

   public Long getCurrentUserId() {
      return getUserDetailsImplDto().getId();
   }

}
