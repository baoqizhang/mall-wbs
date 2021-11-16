package com.thoughtworks.mall.product.domain.service;

import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.product.common.MockProduct;
import com.thoughtworks.mall.product.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest implements MockProduct {
   ProductRepository productRepository = mock(ProductRepository.class);
   ProductService productService = new ProductService(productRepository);

   @Test
   void should_get_product_when_product_id_exist() {
      when(productRepository.findById(1L)).thenReturn(Optional.of(PRODUCT));

      assertEquals(Optional.of(PRODUCT).get(), productService.getProductById(1L));
   }

   @Test
   void should_throw_exception_when_product_not_exist() {
      when(productRepository.findById(1L)).thenReturn(Optional.empty());

      assertThrows(GenericBizException.class, () -> productService.getProductById(1L), "product not exist");
   }
}
