package com.thoughtworks.mall.order.api.assembler;

import com.thoughtworks.mall.infrastructure.constants.SnowflakeIdGenerator;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.infrastructure.enums.OrderStatus;
import java.time.Instant;

public class OrderAssembler {

   public static Order toResource(OrderRequest orderRequest) {
      var order = Order.builder()
         .orderNo(String.valueOf(SnowflakeIdGenerator.next()))
         .actualPrice(orderRequest.getActualPrice())
         .amount(orderRequest.getAmount())
         .status(OrderStatus.WAIT_PAYMENT)
         .build();
      order.updateBasicInfo(Instant.now(), Instant.now());

      return order;
   }
}
