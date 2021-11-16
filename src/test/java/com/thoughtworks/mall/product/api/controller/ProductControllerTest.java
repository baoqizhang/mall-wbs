package com.thoughtworks.mall.product.api.controller;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import com.thoughtworks.mall.infrastructure.exception.BizException;
import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.product.api.assembler.ProductAssembler;
import com.thoughtworks.mall.product.common.MockProduct;
import com.thoughtworks.mall.product.domain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {ProductController.class, ProductAssembler.class})
@ComponentScan(basePackageClasses = BizException.class)
class ProductControllerTest implements MockProduct {
   @Autowired
   MockMvc mockMvc;

   @MockBean
   ProductService productService;

   @Test
   @WithMockUser(username = "admin")
   void should_return_200_when_get_product_detail_with_right_request_info() throws Exception {
      when(productService.getProductById(1L)).thenReturn(PRODUCT);

      mockMvc.perform(get(Constant.ROOT + "/product/{id}", 1L)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(status().isOk())
         .andReturn();
   }

   @Test
   @WithMockUser(username = "admin")
   void should_throw_exception_when_product_not_exist() throws Exception {
      when(productService.getProductById(2L)).thenThrow(new GenericBizException(HttpStatus.BAD_REQUEST, "product not exist"));

      mockMvc.perform(get(Constant.ROOT + "/product/{id}", 2L)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
         .andExpect(status().isBadRequest())
         .andExpect(jsonPath("$.reason.messages").value("product not exist"))
         .andReturn();
   }
}
