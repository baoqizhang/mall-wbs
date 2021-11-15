package com.thoughtworks.mall.user.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity extends AbstractEntity {
   private String name;

   private String username;

   private String password;
}
