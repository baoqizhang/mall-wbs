package com.thoughtworks.mall.product.infrastructure.repository;

import com.thoughtworks.mall.product.domain.entity.ProductSkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSkuEntity, Long> {
}
