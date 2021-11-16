package com.thoughtworks.mall.user.domain.entity;

import com.thoughtworks.mall.infrastructure.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends AbstractEntity {
   private String name;

   private String username;

   private String password;

   @OneToMany(cascade = {CascadeType.ALL})
   private List<UserAddress> userAddressList;
}
