package com.thoughtworks.mall.product.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Product extends AbstractEntity {
   private String title;

   private String desc;

   private Boolean saleable;

   @OneToMany(fetch = FetchType.LAZY)
   private List<ProductSku> productSkuList;

}
