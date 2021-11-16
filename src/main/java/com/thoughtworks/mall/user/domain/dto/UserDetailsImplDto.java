package com.thoughtworks.mall.user.domain.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UserDetailsImplDto extends User {
   private final Long id;

   public UserDetailsImplDto(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
      this.id = id;
   }
}
