package com.thoughtworks.mall.order.common;

import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.infrastructure.enums.OrderStatus;

public interface MockOrder {

   Order ORDER = Order.builder()
      .orderNo("234234")
      .amount(324234)
      .actualPrice(32423)
      .status(OrderStatus.WAIT_PAYMENT)
      .build();
}
