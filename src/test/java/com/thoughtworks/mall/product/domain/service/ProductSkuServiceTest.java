package com.thoughtworks.mall.product.domain.service;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.security.common.WithMockUserImpl;
import com.thoughtworks.mall.product.common.MockProductSku;
import com.thoughtworks.mall.product.infrastructure.repository.ProductRepository;
import com.thoughtworks.mall.product.infrastructure.repository.ProductSkuRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductSkuServiceTest implements MockProductSku {
   ProductSkuRepository productSkuRepository = mock(ProductSkuRepository.class);
   ProductSkuService productSkuService = new ProductSkuService(productSkuRepository);

   @Test
   @WithMockUserImpl
   void should_return_right_result_when_product_sku_id_exist() {
      when(productSkuRepository.findAllById(List.of(1L))).thenReturn(List.of(PRODUCT_SKU));

      assertEquals(true, productSkuService.existsByIds(List.of(1L)));
   }

}
