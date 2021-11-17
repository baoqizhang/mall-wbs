package com.thoughtworks.mall.order.domain.application;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.order.api.assembler.OrderAssembler;
import com.thoughtworks.mall.order.api.assembler.OrderDetailAssembler;
import com.thoughtworks.mall.order.api.request.OrderDetailRequest;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.domain.service.OrderDetailService;
import com.thoughtworks.mall.order.domain.service.OrderService;
import com.thoughtworks.mall.product.domain.service.ProductSkuService;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

   private final OrderAssembler orderAssembler;
   private final OrderDetailAssembler orderDetailAssembler;
   private final UserAddressService userAddressService;
   private final OrderDetailService orderDetailService;
   private final OrderService orderService;
   private final ProductSkuService productSkuService;

   @Transactional(rollbackFor = Exception.class)
   public void create(OrderRequest orderRequest) {
      var skuIds = orderRequest.getOrderDetailRequests().stream()
         .map(OrderDetailRequest::getSkuId)
         .collect(Collectors.toList());
      var skuIdsExist = productSkuService.existsByIds(skuIds);
      if (!skuIdsExist) {
         throw new GenericBizException("product sku not exist");
      }

      var userAddress = userAddressService.findById(orderRequest.getAddressId());
      var order = orderAssembler.toResource(orderRequest, userAddress);
      orderService.create(order);

      var orderDetails = orderDetailAssembler.toResource(orderRequest.getOrderDetailRequests(), order.getId());
      orderDetailService.create(orderDetails);
   }
}
