package com.thoughtworks.mall.user.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.user.api.assembler.UserAddressAssembler;
import com.thoughtworks.mall.user.api.response.UserAddressResponse;
import com.thoughtworks.mall.user.domain.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constant.ROOT + "/user")
@RequiredArgsConstructor
public class UserController {

   private final UserAddressService userAddressService;
   private final UserAddressAssembler userAddressAssembler;

   @GetMapping("/address")
   public List<UserAddressResponse> getCurrentUserAddress() {
      return userAddressAssembler.toResouce(userAddressService.getCurrentUserAddress());
   }
}
