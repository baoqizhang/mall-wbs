package com.thoughtworks.mall.infrastructure.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        var json = objectMapper.writeValueAsString(Map.of(
                "status", HttpStatus.OK.value(),
                "message", HttpStatus.OK,
                "timestamp", new Date()
        ));
        authentication.setAuthenticated(false);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
