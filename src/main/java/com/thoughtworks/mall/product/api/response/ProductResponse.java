package com.thoughtworks.mall.product.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductResponse {
   @ApiModelProperty(value = "id")
   private Long id;
   @ApiModelProperty(value = "title")
   private String title;
   @ApiModelProperty(value = "describe")
   private String desc;
   @ApiModelProperty(value = "saleable")
   private Boolean saleable;

   private List<ProductSkuResponse> productSkuList;
}
