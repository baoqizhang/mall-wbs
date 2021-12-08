package com.thoughtworks.mall.order.api.assembler;

import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.common.MockOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderAssemblerTest implements MockOrder {

   @Test
   void should_transform_success_when_request_right(){
      var orderRequest = new OrderRequest();
      orderRequest.setAmount(ORDER.getAmount());
      orderRequest.setActualPrice(ORDER.getActualPrice());
      orderRequest.setAddressId(1L);
      orderRequest.setOrderDetailRequests(List.of());
      var order = OrderAssembler.toResource(orderRequest);

      assertEquals(ORDER.getAmount(), order.getAmount());
      assertEquals(ORDER.getActualPrice(), order.getActualPrice());
   }

}
