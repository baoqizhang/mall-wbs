package com.thoughtworks.mall.order.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "orders_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@IdClass(OrderDetail.OrderDetailId.class)
public class OrderDetail {
   @Id
   private Long orderId;

   @Id
   private Long skuId;

   private Integer price;

   private Integer actualPrice;

   private Integer num;

   private Instant createdAt;

   private Instant updatedAt;

   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public static class OrderDetailId implements Serializable {
      private Long orderId;

      private Long skuId;
   }

   public void updateOrderId(Long orderId) {
      this.orderId = orderId;
   }
}
