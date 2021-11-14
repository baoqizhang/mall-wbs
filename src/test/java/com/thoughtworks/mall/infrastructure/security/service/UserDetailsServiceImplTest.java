package com.thoughtworks.mall.infrastructure.security.service;

import com.thoughtworks.mall.user.domain.UserEntity;
import com.thoughtworks.mall.user.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {
   UserRepository userRepository = mock(UserRepository.class);
   UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository);

   @Test
   void should_get_user_details_when_username_exist() {
      var userOptional = Optional.of(new UserEntity("user", "123", "123"));
      var user = userOptional.get();
      var userDetail = new User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList());
      when(userRepository.findByUsername("123")).thenReturn(userOptional);

      assertEquals(userDetail, userDetailsService.loadUserByUsername("123"));
   }

   @Test
   void should_throw_username_not_found_exception_when_username_not_exist() {
      when(userRepository.findByUsername("123")).thenReturn(Optional.empty());

      assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("123"), "username not exit");
   }

}
