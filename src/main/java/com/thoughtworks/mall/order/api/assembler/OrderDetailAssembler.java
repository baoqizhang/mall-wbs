package com.thoughtworks.mall.order.api.assembler;

import com.thoughtworks.mall.order.api.request.OrderDetailRequest;
import com.thoughtworks.mall.order.domain.entity.OrderDetail;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailAssembler {

   public List<OrderDetail> toResource(List<OrderDetailRequest> orderDetailRequests, Long orderId) {
      return orderDetailRequests.stream().map(request -> OrderDetail.builder()
         .orderId(orderId)
         .actualPrice(request.getActualPrice())
         .price(request.getPrice())
         .num(request.getNum())
         .skuId(request.getSkuId())
         .createdAt(Instant.now())
         .updatedAt(Instant.now())
         .build()).collect(Collectors.toList());

   }
}
