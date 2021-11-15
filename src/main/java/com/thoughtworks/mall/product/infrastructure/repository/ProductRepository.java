package com.thoughtworks.mall.product.infrastructure.repository;

import com.thoughtworks.mall.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<ProductEntity, Long> {
}
