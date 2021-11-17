package com.thoughtworks.mall.order.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.order.api.request.OrderDetailRequest;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.domain.application.OrderApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = OrderController.class)
class OrderControllerTest {
   @Autowired
   MockMvc mockMvc;

   @MockBean
   OrderApplicationService orderApplicationService;

   @Test
   @WithMockUserImpl(username = "admin")
   void should_return_200_when_create_order_with_right_request_info() throws Exception {
      var orderDetailRequest = new OrderDetailRequest();
      orderDetailRequest.setSkuId(1L);
      orderDetailRequest.setActualPrice(1111);
      orderDetailRequest.setNum(1);
      orderDetailRequest.setPrice(12122);

      var orderRequest = new OrderRequest();
      orderRequest.setActualPrice(11111);
      orderRequest.setAmount(12222);
      orderRequest.setAddressId(1L);
      orderRequest.setOrderDetailRequests(List.of(orderDetailRequest));

      mockMvc.perform(post(Constant.ROOT + "/order")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(new ObjectMapper().writeValueAsString(orderRequest)))
         .andExpect(status().isCreated())
         .andReturn();
   }

   @Test
   @WithMockUserImpl(username = "admin")
   void should_throw_exception_when_create_order_with_request_field_is_null() throws Exception {
      var orderDetailRequest = new OrderDetailRequest();
      orderDetailRequest.setSkuId(1L);
      orderDetailRequest.setActualPrice(1111);
      orderDetailRequest.setNum(1);
      orderDetailRequest.setPrice(12122);

      var orderRequest = new OrderRequest();
      orderRequest.setActualPrice(null);
      orderRequest.setAmount(12222);
      orderRequest.setAddressId(1L);
      orderRequest.setOrderDetailRequests(List.of(orderDetailRequest));

      mockMvc.perform(post(Constant.ROOT + "/order")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(new ObjectMapper().writeValueAsString(orderRequest)))
         .andExpect(status().is4xxClientError())
         .andReturn();
   }
}
