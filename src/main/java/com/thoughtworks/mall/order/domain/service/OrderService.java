package com.thoughtworks.mall.order.domain.service;

import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderRepository orderRepository;

   public void create(Order order) {
      orderRepository.save(order);
   }
}
