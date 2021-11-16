package com.thoughtworks.mall.user.domain.application;

import com.thoughtworks.mall.user.domain.dto.UserDetailsImplDto;
import com.thoughtworks.mall.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) {
      var user = userRepository.findByUsername(username)
         .orElseThrow(() -> new UsernameNotFoundException("username not exit"));

      return new UserDetailsImplDto(user.getId(), user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList());
   }
}
