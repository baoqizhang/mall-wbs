package com.thoughtworks.mall.infrastructure.security.common;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.user.domain.dto.UserDetailsImplDto;
import org.springframework.security.core.context.SecurityContextHolder;

public class SercurityCommonProvider {

   public static Long getCurrentUserId() {
      var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetailsImplDto) {
         return ((UserDetailsImplDto) principal).getId();
      }
      throw new GenericBizException("not exist user id");
   }

}
