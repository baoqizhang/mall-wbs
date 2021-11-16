package com.thoughtworks.mall.infrastructure.security.common;

import com.thoughtworks.mall.user.domain.dto.UserDetailsImplDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUserImpl> {
   @Override
   public SecurityContext createSecurityContext(WithMockUserImpl customUser) {
      String username = StringUtils.hasLength(customUser.username()) ? customUser.username() : customUser.value();
      Assert.notNull(username, () -> customUser + " cannot have null username on both username and value properties");
      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      for (String authority : customUser.authorities()) {
         grantedAuthorities.add(new SimpleGrantedAuthority(authority));
      }

      if (grantedAuthorities.isEmpty()) {
         for (String role : customUser.roles()) {
            Assert.isTrue(!role.startsWith("ROLE_"), () -> "roles cannot start with ROLE_ Got " + role);
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
         }
      } else if (!(customUser.roles().length == 1 && "USER".equals(customUser.roles()[0]))) {
         throw new IllegalStateException("You cannot define roles attribute " + Arrays.asList(customUser.roles())
            + " with authorities attribute " + Arrays.asList(customUser.authorities()));
      }

      UserDetails principal = new UserDetailsImplDto(customUser.id(), username, customUser.password(), true, true, true, true, grantedAuthorities);
      Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(),
         principal.getAuthorities());
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authentication);
      return context;
   }
}
