package com.thoughtworks.mall.product.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Product extends AbstractEntity {
   private String title;

   private String desc;

   private Boolean saleable;

   @OrderBy("createdAt DESC")
   @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
   private List<ProductSku> productSkuList;
}
