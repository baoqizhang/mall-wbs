package com.thoughtworks.mall.user.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserAddressResponse {
   @ApiModelProperty(name = "id")
   private Long id;
   @ApiModelProperty(name = "收件人姓名")
   private String name;
   @ApiModelProperty(name = "收件人手机号")
   private String phone;
   @ApiModelProperty(name = "收件人地址")
   private String address;
}
