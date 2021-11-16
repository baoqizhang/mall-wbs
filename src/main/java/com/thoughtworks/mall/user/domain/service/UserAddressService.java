package com.thoughtworks.mall.user.domain.service;

import com.thoughtworks.mall.user.domain.dto.UserDetailsImplDto;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import com.thoughtworks.mall.user.infrastructure.repository.UserAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAddressService {

   private final UserAddressRepository userAddressRepository;

   public List<UserAddress> getCurrentUserAddress() {
      var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Long userId = null;
      if (principal instanceof UserDetailsImplDto) {
         userId = ((UserDetailsImplDto) principal).getId();
      }
      return userAddressRepository.findAllByUserId(userId);
   }
}
