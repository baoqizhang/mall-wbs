package com.thoughtworks.mall.user.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class UserAddressRequest {
   @ApiModelProperty(value = "姓名")
   private String name;
   @ApiModelProperty(value = "地址")
   private String address;
   @ApiModelProperty(value = "手机号")
   private String phone;
}
