package com.thoughtworks.mall.infrastructure.exception.handler;

import com.thoughtworks.mall.infrastructure.exception.converter.WebExceptionConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
   private final List<WebExceptionConverter> converters;

   public GlobalExceptionHandler(ObjectProvider<List<WebExceptionConverter>> objectProviderList) {
      this.converters = objectProviderList.getIfAvailable(List::of);
   }

   @ExceptionHandler
   public ResponseEntity<Map<String, Object>> handler(Throwable throwable, HttpServletRequest request) {
      log.warn("Caught an error.", throwable);
      return converters.stream()
         .filter(converter -> converter.supports(throwable.getClass()))
         .findFirst()
         .map(converter -> converter.convert(throwable, request))
         .orElseThrow(() -> new RuntimeException("Can not find exception converter."));
   }
}
