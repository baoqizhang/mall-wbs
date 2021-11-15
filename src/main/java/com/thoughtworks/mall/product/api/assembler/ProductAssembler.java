package com.thoughtworks.mall.product.api.assembler;

import com.thoughtworks.mall.product.api.response.ProductResponse;
import com.thoughtworks.mall.product.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductAssembler {
   public ProductResponse toResouce(Product product) {
      var productSkuResponseList = product.getProductSkuList()
         .stream().map(ProductSkuAssembler::toReResouce)
         .collect(Collectors.toList());

      return new ProductResponse()
         .setId(product.getId())
         .setDesc(product.getDesc())
         .setTitle(product.getTitle())
         .setSaleable(product.getSaleable())
         .setProductSkuList(productSkuResponseList);
   }
}
