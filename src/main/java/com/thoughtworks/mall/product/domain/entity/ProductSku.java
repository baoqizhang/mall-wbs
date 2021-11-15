package com.thoughtworks.mall.product.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "product_sku")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductSku extends AbstractEntity {
   private Long productId;

   private Integer price;

   private String title;

   @Type(type = "json")
   @Column(columnDefinition = "json")
   private String images;

   @Type(type = "json")
   @Column(columnDefinition = "json")
   private String param;

   private Boolean saleable;
}
