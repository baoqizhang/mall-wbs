package com.thoughtworks.mall.order.common;

import com.thoughtworks.mall.order.domain.entity.OrderDetail;

public interface MockOrderDetail {
   OrderDetail ORDER_DETAIL = OrderDetail.builder()
      .orderId(1L)
      .num(1)
      .actualPrice(1111)
      .skuId(1L)
      .price(11112)
      .build();
}
