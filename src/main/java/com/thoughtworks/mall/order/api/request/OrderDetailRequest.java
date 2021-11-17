package com.thoughtworks.mall.order.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDetailRequest {

   @ApiModelProperty(name = "商品id")
   @NotNull(message = "product sku not null")
   private Long skuId;

   @ApiModelProperty(name = "原价格 单位分")
   @NotNull(message = "price not null")
   private Integer price;

   @ApiModelProperty(name = "实际支付价格 单位分")
   @NotNull(message = "actual price not null")
   private Integer actualPrice;

   @ApiModelProperty(name = "数量")
   @NotNull(message = "product sku number not null")
   private Integer num;
}
