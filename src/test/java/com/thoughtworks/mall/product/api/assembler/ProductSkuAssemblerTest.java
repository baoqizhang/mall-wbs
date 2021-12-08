package com.thoughtworks.mall.product.api.assembler;

import com.thoughtworks.mall.product.common.MockProductSku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSkuAssemblerTest implements MockProductSku {
   @Test
   void should_transform_success_when_request_right(){
      var productSkuResponse = ProductSkuAssembler.toResource(PRODUCT_SKU);

      assertEquals(PRODUCT_SKU.getId(), productSkuResponse.getId());
      assertEquals(PRODUCT_SKU.getPrice(), productSkuResponse.getPrice());
      assertEquals(PRODUCT_SKU.getTitle(), productSkuResponse.getTitle());
      assertEquals(PRODUCT_SKU.getSaleable(), productSkuResponse.getSaleable());
      assertEquals(PRODUCT_SKU.getImages(), productSkuResponse.getImages());
   }
}
