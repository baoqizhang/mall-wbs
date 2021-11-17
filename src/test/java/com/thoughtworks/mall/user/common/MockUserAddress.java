package com.thoughtworks.mall.user.common;

import com.thoughtworks.mall.user.domain.entity.UserAddress;

public interface MockUserAddress {
   UserAddress USER_ADDRESS = UserAddress.builder()
      .address("四川省成都市")
      .userId(1L)
      .name("张三")
      .phone("13345678901")
      .prime(true)
      .build();
}
