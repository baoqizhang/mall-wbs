package com.thoughtworks.mall.order.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

   @ApiModelProperty(name = "订单金额 单位分")
   @NotNull(message = "order amount not null")
   private Integer amount;

   @ApiModelProperty(name = "实际支付金额 单位分")
   @NotNull(message = "order actual price not null")
   private Integer actualPrice;

   @ApiModelProperty(name = "地址id")
   @NotNull(message = "address not null")
   private Long addressId;

   @NotEmpty(message = "order detail not empty")
   private List<OrderDetailRequest> orderDetailRequests;
}
