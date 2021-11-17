package com.thoughtworks.mall.order.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.order.api.request.OrderRequest;
import com.thoughtworks.mall.order.domain.application.OrderApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constant.ROOT + "/order")
@RequiredArgsConstructor
@Api(tags = {"order api"})
public class OrderController {

   private final OrderApplicationService orderApplicationService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation("create order")
   public void create(@RequestBody @Valid OrderRequest orderRequest) {
      orderApplicationService.create(orderRequest);
   }
}
