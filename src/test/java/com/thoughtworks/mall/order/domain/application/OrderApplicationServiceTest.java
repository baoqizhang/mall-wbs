package com.thoughtworks.mall.order.domain.application;

import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.order.api.assembler.OrderAssembler;
import com.thoughtworks.mall.order.api.assembler.OrderDetailAssembler;
import com.thoughtworks.mall.order.api.request.OrderDetailRequest;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.common.MockOrder;
import com.thoughtworks.mall.order.common.MockOrderDetail;
import com.thoughtworks.mall.order.domain.service.OrderDetailService;
import com.thoughtworks.mall.order.domain.service.OrderService;
import com.thoughtworks.mall.product.domain.service.ProductSkuService;
import com.thoughtworks.mall.user.common.MockUserAddress;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderApplicationServiceTest implements MockUserAddress, MockOrder, MockOrderDetail {

   OrderAssembler orderAssembler = mock(OrderAssembler.class);
   OrderDetailAssembler orderDetailAssembler = mock(OrderDetailAssembler.class);
   UserAddressService userAddressService = mock(UserAddressService.class);
   OrderDetailService orderDetailService = mock(OrderDetailService.class);
   OrderService orderService = mock(OrderService.class);
   ProductSkuService productSkuService = mock(ProductSkuService.class);

   OrderApplicationService orderApplicationService = new OrderApplicationService(orderAssembler, orderDetailAssembler,
      userAddressService, orderDetailService, orderService, productSkuService);

   @Test
   @WithMockUserImpl
   void should_create_success_with_right_request_info() {
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

      when(productSkuService.existsByIds(List.of(1L))).thenReturn(true);
      when(userAddressService.findById(1L)).thenReturn(USER_ADDRESS);
      when(orderAssembler.toResource(orderRequest, USER_ADDRESS)).thenReturn(ORDER);
      when(orderDetailAssembler.toResource(List.of(orderDetailRequest), ORDER.getId())).thenReturn(List.of(ORDER_DETAIL));
      assertDoesNotThrow(() -> orderApplicationService.create(orderRequest));
   }

}
