package com.thoughtworks.mall.order.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import com.thoughtworks.mall.order.infrastructure.enums.OrderStatus;
import com.thoughtworks.mall.user.domain.entity.UserAddress;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity {

   private String orderNo;

   private Integer amount;

   private Integer actualPrice;

   @Enumerated(EnumType.STRING)
   private OrderStatus status;

   @ManyToOne
   @JoinColumn(name = "address_id", referencedColumnName = "id")
   private UserAddress userAddress;

   @OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER)
   private List<OrderDetail> orderDetails;

}
