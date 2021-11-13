package com.thoughtworks.mall.greet.api;

import com.thoughtworks.mall.infrastructure.constants.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.ROOT + "/greet")
public class GreetController {

    @GetMapping
    public String greet() {
        return "hello mall";
    }
}
