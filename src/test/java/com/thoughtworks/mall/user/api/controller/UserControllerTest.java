package com.thoughtworks.mall.user.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.infrastructure.exception.BizException;
import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.user.api.assembler.UserAddressAssembler;
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

}
