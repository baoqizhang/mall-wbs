package com.thoughtworks.mall.infrastructure.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.infrastructure.exception.GenericBizException;
import com.thoughtworks.mall.infrastructure.exception.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserLoginFailureHandler implements AuthenticationFailureHandler, AuthenticationEntryPoint, AccessDeniedHandler {

   private final ObjectMapper objectMapper;
   private final GlobalExceptionHandler globalExceptionHandler;

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
      rethrow(request, response, exception);
   }

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
      rethrow(request, response, exception);
   }

   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
      rethrow(request, response, exception);
   }

   protected void rethrow(HttpServletRequest request,
                          HttpServletResponse response, RuntimeException throwable) {

      if (log.isDebugEnabled()) {
         log.debug("Authenticate failed.", throwable);
      }

      try {
         var responseEntity = globalExceptionHandler.handler(throwable, request);
         responseEntity.getHeaders().forEach((name, values) ->
            values.forEach(value -> response.addHeader(name, value)));

         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         response.setStatus(responseEntity.getStatusCodeValue());
         if (responseEntity.hasBody()) {
            var body = objectMapper.writeValueAsString(responseEntity.getBody());
            response.getWriter().write(body);
         }
      } catch (Throwable rethrow) {
         log.warn("Handle authentication failed.", rethrow);
         throw new GenericBizException(rethrow);
      }
   }
}
