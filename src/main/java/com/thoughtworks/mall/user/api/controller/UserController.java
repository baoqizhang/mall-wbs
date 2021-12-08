package com.thoughtworks.mall.user.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.user.api.assembler.UserAddressAssembler;
import com.thoughtworks.mall.user.api.request.UserAddressRequest;
import com.thoughtworks.mall.user.api.response.UserAddressResponse;
import com.thoughtworks.mall.user.application.UserAddressApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constant.ROOT + "/user")
@RequiredArgsConstructor
public class UserController {

   private final UserAddressApplicationService userApplicationService;

   @GetMapping("/address")
   @ApiOperation("获取当前用户地址")
   public List<UserAddressResponse> getCurrentUserAddress() {
      return UserAddressAssembler.toResource(userApplicationService.getCurrentUserAddress());
   }

   @PostMapping("/address")
   @ApiOperation("创建当前用户地址")
   @ResponseStatus(HttpStatus.CREATED)
   public void createAddress(@RequestBody @Valid UserAddressRequest userAddressRequest) {
      var userAddress = UserAddressAssembler.toResource(userAddressRequest);
      userApplicationService.createCurrentUserAddress(userAddress);
   }
}
