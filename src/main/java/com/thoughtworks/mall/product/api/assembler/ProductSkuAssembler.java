package com.thoughtworks.mall.product.api.assembler;

import com.thoughtworks.mall.product.api.response.ProductSkuResponse;
import com.thoughtworks.mall.product.domain.entity.ProductSku;
import org.springframework.stereotype.Component;

@Component
public class ProductSkuAssembler {
   public static ProductSkuResponse toResource(ProductSku productSku) {
      return new ProductSkuResponse()
         .setId(productSku.getId())
         .setImages(productSku.getImages())
         .setParam(productSku.getParam())
         .setPrice(productSku.getPrice())
         .setSaleable(productSku.getSaleable())
         .setTitle(productSku.getTitle());
   }
}
