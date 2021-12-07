package com.thoughtworks.mall.user.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserAddress extends AbstractEntity {

   private Long userId;

   private String name;

   private String phone;

   private Boolean prime;

   private String address;

   public void updateUserId(Long userId) {
      this.userId = userId;
   }

}
