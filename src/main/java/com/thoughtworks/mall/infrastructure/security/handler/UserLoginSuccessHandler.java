package com.thoughtworks.mall.infrastructure.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        var json = objectMapper.writeValueAsString(Map.of(
            "status", HttpStatus.OK.value(),
            "message", HttpStatus.OK,
            "timestamp", new Date()
        ));

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
