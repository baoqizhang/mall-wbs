package com.thoughtworks.mall.product.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.product.api.assembler.ProductAssembler;
import com.thoughtworks.mall.product.api.response.ProductResponse;
import com.thoughtworks.mall.product.domain.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ROOT + "/product")
@RequiredArgsConstructor
@Api(tags = "product api")
public class ProductController {

   private final ProductService productService;

   @GetMapping("/{id}")
   @ApiOperation(value = "get product by id")
   public ProductResponse getProduct(@PathVariable("id") Long id) {
      return ProductAssembler.toResource(productService.getProductById(id));
   }

}
