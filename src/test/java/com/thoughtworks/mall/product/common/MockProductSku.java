package com.thoughtworks.mall.product.common;

import com.thoughtworks.mall.product.domain.entity.ProductSku;

public interface MockProductSku {
   ProductSku PRODUCT_SKU = ProductSku.builder()
      .images("")
      .param("")
      .price(100)
      .saleable(true)
      .title("title")
      .build();
}
