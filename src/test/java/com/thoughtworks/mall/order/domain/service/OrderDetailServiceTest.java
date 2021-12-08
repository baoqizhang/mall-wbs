package com.thoughtworks.mall.order.domain.service;

import com.thoughtworks.mall.order.common.MockOrderDetail;
import com.thoughtworks.mall.order.infrastructure.repository.OrderDetailRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderDetailServiceTest implements MockOrderDetail {

   OrderDetailRepository orderDetailRepository = mock(OrderDetailRepository.class);

   OrderDetailService orderDetailService = new OrderDetailService(orderDetailRepository);


   @Test
   void should_success_create_order_details_with_right_info() {
      assertDoesNotThrow(() -> orderDetailService.create(List.of(ORDER_DETAIL)));
   }

}
