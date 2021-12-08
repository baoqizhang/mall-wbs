package com.thoughtworks.mall.product.api.assembler;

import com.thoughtworks.mall.order.api.assembler.OrderAssembler;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.product.common.MockProduct;
import com.thoughtworks.mall.product.domain.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductAssemblerTest implements MockProduct {

   @Test
   void should_transform_success_when_request_right(){
      var productResponse = ProductAssembler.toResource(PRODUCT);

      assertEquals(PRODUCT.getId(), productResponse.getId());
      assertEquals(PRODUCT.getTitle(), productResponse.getTitle());
      assertEquals(PRODUCT.getDesc(), productResponse.getDesc());
      assertEquals(PRODUCT.getSaleable(), productResponse.getSaleable());
      assertEquals(PRODUCT.getProductSkuList().size(), productResponse.getProductSkuList().size());
   }

}
