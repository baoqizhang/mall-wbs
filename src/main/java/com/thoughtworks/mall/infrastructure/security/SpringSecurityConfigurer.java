package com.thoughtworks.mall.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.infrastructure.exception.handler.GlobalExceptionHandler;
import com.thoughtworks.mall.infrastructure.security.handler.UserLoginFailureHandler;
import com.thoughtworks.mall.infrastructure.security.handler.UserLoginSuccessHandler;
import com.thoughtworks.mall.infrastructure.security.handler.UserLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

   private final GlobalExceptionHandler globalExceptionHandler;
   private final ObjectMapper objectMapper;

   private static final String LOGOUT_PATH_PATTERN = Constant.ROOT + "/logout";
   private static final String LOGIN_PATH_PATTERN = Constant.ROOT + "/login";

   @Override
   public void configure(WebSecurity web) {
      web.ignoring().antMatchers(HttpMethod.GET,
         "/swagger-resources",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/swagger-ui/**",
         "/v3/api-docs");
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
         .csrf().disable()
         .authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .successHandler(new UserLoginSuccessHandler())
         .failureHandler(new UserLoginFailureHandler(objectMapper, globalExceptionHandler))
         .loginPage(LOGIN_PATH_PATTERN)
         .and()
         .logout()
         .logoutUrl(LOGOUT_PATH_PATTERN)
         .logoutSuccessHandler(new UserLogoutSuccessHandler())
         .and()
         .httpBasic();
   }

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
