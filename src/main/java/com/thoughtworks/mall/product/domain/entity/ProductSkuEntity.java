package com.thoughtworks.mall.product.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_sku")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuEntity extends AbstractEntity {
   private Long productId;

   private Integer price;

   private String title;

   private String images;

   private String param;

   private Boolean saleable;
}
