package com.thoughtworks.mall.product.common;

import com.thoughtworks.mall.product.domain.entity.Product;
import com.thoughtworks.mall.product.domain.entity.ProductSku;

import java.util.List;


public interface MockProduct {

   Product PRODUCT = Product.builder()
      .desc("desc")
      .saleable(true)
      .title("title")
      .productSkuList(List.of(ProductSku.builder()
         .productId(1L)
         .images("")
         .param("")
         .price(100)
         .saleable(true)
         .title("title")
         .build()))
      .build();
}
