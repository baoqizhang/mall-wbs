package com.thoughtworks.mall.product.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSkuResponse {
   @ApiModelProperty(value = "id")
   private Long id;
   @ApiModelProperty(value = "price")
   private Integer price;
   @ApiModelProperty(value = "title")
   private String title;
   @ApiModelProperty(value = "images")
   private String images;
   @ApiModelProperty(value = "param")
   private String param;
   @ApiModelProperty(value = "saleable")
   private Boolean saleable;
}
