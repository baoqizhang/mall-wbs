package com.thoughtworks.mall.product.api.assembler;

import com.thoughtworks.mall.product.api.response.ProductResponse;
import com.thoughtworks.mall.product.domain.entity.Product;

import java.util.stream.Collectors;

public class ProductAssembler {
   public static ProductResponse toResource(Product product) {
      var productSkuResponseList = product.getProductSkuList()
         .stream().map(ProductSkuAssembler::toResource)
         .collect(Collectors.toList());

      return new ProductResponse()
         .setId(product.getId())
         .setDesc(product.getDesc())
         .setTitle(product.getTitle())
         .setSaleable(product.getSaleable())
         .setProductSkuList(productSkuResponseList);
   }
}
