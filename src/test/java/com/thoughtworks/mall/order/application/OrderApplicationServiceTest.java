package com.thoughtworks.mall.order.application;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.security.common.SecurityCommonProvider;
import com.thoughtworks.mall.order.common.MockOrder;
import com.thoughtworks.mall.order.common.MockOrderDetail;
import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.domain.entity.OrderDetail;
import com.thoughtworks.mall.order.domain.service.OrderDetailService;
import com.thoughtworks.mall.order.domain.service.OrderService;
import com.thoughtworks.mall.order.infrastructure.enums.OrderStatus;
import com.thoughtworks.mall.product.domain.service.ProductSkuService;
import com.thoughtworks.mall.user.common.MockUserAddress;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderApplicationServiceTest implements MockUserAddress, MockOrder, MockOrderDetail {

   UserAddressService userAddressService = mock(UserAddressService.class);
   OrderDetailService orderDetailService = mock(OrderDetailService.class);
   OrderService orderService = mock(OrderService.class);
   ProductSkuService productSkuService = mock(ProductSkuService.class);
   SecurityCommonProvider securityCommonProvider = mock(SecurityCommonProvider.class);

   OrderApplicationService orderApplicationService = new OrderApplicationService(userAddressService, orderDetailService,
      orderService, productSkuService, securityCommonProvider);

   private Order order;
   private OrderDetail orderDetail;

   @BeforeEach
   void setUp() {
      orderDetail = new OrderDetail(null, 1L, 111, 111, 1, Instant.now(), Instant.now());
      order = new Order(null, 1111, 111, OrderStatus.WAIT_PAYMENT, USER_ADDRESS, List.of(orderDetail));
   }

   @Test
   void should_create_success_with_right_request_info() {
      when(productSkuService.existsByIds(List.of(1L))).thenReturn(true);
      when(securityCommonProvider.getCurrentUserId()).thenReturn(1L);
      when(userAddressService.findByUserIdAndId(1L, 1L)).thenReturn(USER_ADDRESS);
      assertDoesNotThrow(() -> orderApplicationService.create(order, List.of(orderDetail), 1L));
   }

   @Test
   void should_throw_generic_biz_exception_when_sku_id_not_exist() {
      when(productSkuService.existsByIds(List.of(1L))).thenReturn(false);
      assertThrows(GenericBizException.class, () -> orderApplicationService.create(order, List.of(orderDetail), 1L), "product sku not exist");
   }

}
