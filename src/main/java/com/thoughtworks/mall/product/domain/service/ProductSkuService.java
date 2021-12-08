package com.thoughtworks.mall.product.domain.service;

import com.thoughtworks.mall.product.infrastructure.repository.ProductSkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSkuService {
   private final ProductSkuRepository productSkuRepository;

   public Boolean existsByIds(List<Long> skuIds) {
      var allById = productSkuRepository.findAllById(skuIds);

      return allById.size() == skuIds.size();
   }
}
