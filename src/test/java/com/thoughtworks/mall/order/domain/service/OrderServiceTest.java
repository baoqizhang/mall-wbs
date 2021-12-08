package com.thoughtworks.mall.order.domain.service;

import com.thoughtworks.mall.order.common.MockOrder;
import com.thoughtworks.mall.order.infrastructure.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderServiceTest implements MockOrder {
   OrderRepository orderRepository = mock(OrderRepository.class);
   OrderService orderService = new OrderService(orderRepository);

   @Test
   void should_create_success_when_order_info_right() {
      assertDoesNotThrow(() -> orderService.create(ORDER));
   }

}
