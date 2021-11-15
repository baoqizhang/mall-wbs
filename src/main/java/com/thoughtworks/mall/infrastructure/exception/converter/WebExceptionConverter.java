package com.thoughtworks.mall.infrastructure.exception.converter;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface WebExceptionConverter {

   boolean supports(Class<?> throwableType);

   ResponseEntity<Map<String, Object>> convert(Throwable throwable, HttpServletRequest request);
}
