package com.thoughtworks.mall.product.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
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

   private Integer price;

   private String title;

   @Type(type = "json")
   @Column(columnDefinition = "json")
   private String images;

   @Type(type = "json")
   @Column(columnDefinition = "json")
   private String param;

   private Boolean saleable;

   @JoinColumn(name = "product_id", referencedColumnName = "id")
   @ManyToOne
   private Product product;
}
