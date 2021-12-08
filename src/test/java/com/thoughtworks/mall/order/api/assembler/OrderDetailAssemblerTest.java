package com.thoughtworks.mall.order.api.assembler;

import com.thoughtworks.mall.order.api.request.OrderDetailRequest;
import com.thoughtworks.mall.order.common.MockOrderDetail;
import com.thoughtworks.mall.order.domain.entity.OrderDetail;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailAssemblerTest implements MockOrderDetail {
   @Test
   void should_transform_success_when_request_right() {
      var orderDetailRequest = new OrderDetailRequest();
      orderDetailRequest.setSkuId(ORDER_DETAIL.getSkuId());
      orderDetailRequest.setPrice(ORDER_DETAIL.getPrice());
      orderDetailRequest.setActualPrice(ORDER_DETAIL.getActualPrice());
      orderDetailRequest.setNum(ORDER_DETAIL.getNum());

      var orderDetail = OrderDetailAssembler.toResource(List.of(orderDetailRequest)).get(0);

      assertEquals(ORDER_DETAIL.getSkuId(), orderDetail.getSkuId());
      assertEquals(ORDER_DETAIL.getActualPrice(), orderDetail.getActualPrice());
      assertEquals(ORDER_DETAIL.getPrice(), orderDetail.getPrice());
      assertEquals(ORDER_DETAIL.getNum(), orderDetail.getNum());
   }
}
