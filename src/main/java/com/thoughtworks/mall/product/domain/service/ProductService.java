package com.thoughtworks.mall.product.domain.service;

import com.thoughtworks.mall.infrastructure.exception.BizException;
import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.product.api.response.ProductResponse;
import com.thoughtworks.mall.product.domain.entity.Product;
import com.thoughtworks.mall.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository productRepository;

   public Product getProductById(long id) {
      return productRepository.findById(id).orElseThrow(() -> new GenericBizException(HttpStatus.BAD_REQUEST, "product not exist"));
   }
}
