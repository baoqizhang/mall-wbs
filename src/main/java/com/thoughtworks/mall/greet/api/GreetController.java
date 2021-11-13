package com.thoughtworks.mall.greet.api;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ROOT + "/greet")
@Api(tags = "greet api")
public class GreetController {

    @GetMapping
    @ApiOperation("get greet")
    public String greet() {
        return "hello mall";
    }
}
