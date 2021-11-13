package com.thoughtworks.mall.greet.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.thoughtworks.mall.infrastructure.constants.Constant.ROOT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = GreetController.class)
class GreetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_200_with_right_request_info() throws Exception {

        mockMvc.perform(get(ROOT + "/greet")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
}
