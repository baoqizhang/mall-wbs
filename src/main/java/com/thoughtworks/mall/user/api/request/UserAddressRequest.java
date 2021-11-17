package com.thoughtworks.mall.user.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserAddressRequest {
   @ApiModelProperty(value = "姓名")
   @NotBlank(message = "name not null.")
   private String name;

   @ApiModelProperty(value = "地址")
   @NotBlank(message = "address not null.")
   private String address;

   @ApiModelProperty(value = "手机号")
   @NotBlank(message = "phone not null.")
   @Length(min = 11, max = 11, message = "phone length should is 11.")
   @Pattern(regexp = "^[1][3-9][0-9]{9}$", message = "phone reg error.")
   private String phone;
}
