package com.thoughtworks.mall.order.application;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.security.common.SecurityCommonProvider;
import com.thoughtworks.mall.order.domain.entity.Order;
import com.thoughtworks.mall.order.domain.entity.OrderDetail;
import com.thoughtworks.mall.order.domain.service.OrderDetailService;
import com.thoughtworks.mall.order.domain.service.OrderService;
import com.thoughtworks.mall.product.domain.service.ProductSkuService;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

   private final UserAddressService userAddressService;
   private final OrderDetailService orderDetailService;
   private final OrderService orderService;
   private final ProductSkuService productSkuService;
   private final SecurityCommonProvider securityCommonProvider;

   @Transactional(rollbackFor = Exception.class)
   public void create(Order order, List<OrderDetail> orderDetails, Long addressId) {
      var skuIds = orderDetails.stream()
         .map(OrderDetail::getSkuId)
         .collect(Collectors.toList());
      var skuIdsExist = productSkuService.existsByIds(skuIds);
      if (Boolean.FALSE.equals(skuIdsExist)) {
         throw new GenericBizException("product sku not exist");
      }

      var userAddress = userAddressService.findByUserIdAndId(securityCommonProvider.getCurrentUserId(),addressId);
      order.updateUserAddress(userAddress);
      orderService.create(order);

      orderDetails.forEach(orderDetail -> orderDetail.updateOrderId(order.getId()));
      orderDetailService.create(orderDetails);
   }
}
