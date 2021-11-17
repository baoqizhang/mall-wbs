package com.thoughtworks.mall.order.domain.service;

import com.thoughtworks.mall.order.domain.entity.OrderDetail;
import com.thoughtworks.mall.order.infrastructure.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

   private final OrderDetailRepository orderDetailRepository;

   public void create(List<OrderDetail> orderDetails) {
      orderDetailRepository.saveAll(orderDetails);
   }
}
