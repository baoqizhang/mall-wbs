package com.thoughtworks.mall.user.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.infrastructure.exception.BizException;
import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.user.api.assembler.UserAddressAssembler;
import com.thoughtworks.mall.user.api.request.UserAddressRequest;
import com.thoughtworks.mall.user.common.MockUserAddress;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackageClasses = BizException.class)
@WebMvcTest(value = {UserController.class, UserAddressAssembler.class})
class UserControllerTest implements MockUserAddress {

   @Autowired
   MockMvc mockMvc;

   @MockBean
   UserAddressService userAddressService;

   @Test
   @WithMockUserImpl(username = "admin")
   void should_return_200_when_get_user_detail_with_right_request_info() throws Exception {
      when(userAddressService.getCurrentUserAddress()).thenReturn(List.of(USER_ADDRESS));

      mockMvc.perform(get(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(status().isOk())
         .andReturn();
   }

   @Test
   @WithMockUserImpl
   void should_create_current_user_address_when_create_with_right_request_info() throws Exception {
      mockMvc.perform(post(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(USER_ADDRESS)))
         .andExpect(status().isCreated())
         .andReturn();
   }

   @Test
   @WithMockUserImpl
   void should_throw_valid_exception_when_create_with_phone_reg_error() throws Exception {
      var build = new UserAddressRequest();
      build.setAddress("address");
      build.setName("name");
      build.setPhone("12345678901");

      mockMvc.perform(post(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(build)))
         .andExpect(status().is4xxClientError())
         .andExpect(jsonPath("$.reason.messages").value("phone reg error."))
         .andReturn();
   }

   @Test
   @WithMockUserImpl
   void should_throw_valid_exception_when_create_with_phone_length_error() throws Exception {
      var build = new UserAddressRequest();
      build.setAddress("address");
      build.setName("name");
      build.setPhone("12345");

      mockMvc.perform(post(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(build)))
         .andExpect(status().is4xxClientError())
         .andExpect(jsonPath("$.reason.messages").value("phone length should is 11."))
         .andReturn();
   }

   @Test
   @WithMockUserImpl
   void should_throw_valid_exception_when_create_with_name_is_null() throws Exception {
      var build = new UserAddressRequest();
      build.setAddress("address");
      build.setName(null);
      build.setPhone("13245678901");

      mockMvc.perform(post(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(build)))
         .andExpect(status().is4xxClientError())
         .andExpect(jsonPath("$.reason.messages").value("name not null."))
         .andReturn();
   }

   @Test
   @WithMockUserImpl
   void should_throw_valid_exception_when_create_with_address_is_null() throws Exception {
      var build = new UserAddressRequest();
      build.setAddress(null);
      build.setName("name");
      build.setPhone("13245678901");

      mockMvc.perform(post(Constant.ROOT + "/user/address")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(build)))
         .andExpect(status().is4xxClientError())
         .andExpect(jsonPath("$.reason.messages").value("address not null."))
         .andReturn();
   }
}
