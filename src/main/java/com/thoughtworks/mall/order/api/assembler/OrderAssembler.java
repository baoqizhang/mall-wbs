package com.thoughtworks.mall.order.api.assembler;

import com.thoughtworks.mall.infrastructure.constants.SnowflakeIdGenerator;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.infrastructure.enums.OrderStatus;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
public class OrderAssembler {

   public Order toResource(OrderRequest orderRequest, UserAddress userAddress) {
      var order = Order.builder()
         .orderNo(String.valueOf(SnowflakeIdGenerator.next()))
         .actualPrice(orderRequest.getActualPrice())
         .amount(orderRequest.getAmount())
         .status(OrderStatus.WAIT_PAYMENT)
         .userAddress(userAddress)
         .build();
      order.updateBasicInfo(Instant.now(), Instant.now());

      return order;
   }
}
