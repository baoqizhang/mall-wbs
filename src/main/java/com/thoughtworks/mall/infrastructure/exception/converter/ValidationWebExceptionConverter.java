package com.thoughtworks.mall.infrastructure.exception.converter;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Stream;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ValidationWebExceptionConverter extends AbstractExceptionConverter<Exception> {

   @Override
   public boolean supports(Class<?> throwableType) {
      return Stream.of(BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class)
         .anyMatch(targetType -> targetType.isAssignableFrom(throwableType));
   }

   @Override
   protected String getMessage(Exception exception, MergedAnnotation<ResponseStatus> metadata) {
      return getBindingResult(exception, metadata);
   }

   @Override
   protected HttpStatus getHttpStatus(Exception exception, MergedAnnotation<ResponseStatus> metadata) {
      return HttpStatus.BAD_REQUEST;
   }

   private String convertBindingResult(Exception exception, MergedAnnotation<ResponseStatus> metadata, BindingResult result) {
      if (result == null || result.getFieldErrorCount() < 1) {
         return getHttpStatus(exception, metadata).getReasonPhrase();
      }
      var error = result.getFieldErrors().get(0);
      return error.getDefaultMessage();
   }

   private String getBindingResult(Exception exception, MergedAnnotation<ResponseStatus> metadata) {
      if (ClassUtils.isAssignableValue(BindException.class, exception)) {
         var bindingResult = ((BindException) exception).getBindingResult();
         return convertBindingResult(exception, metadata, bindingResult);
      }
      if (ClassUtils.isAssignableValue(MethodArgumentNotValidException.class, exception)) {
         var bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
         return convertBindingResult(exception, metadata, bindingResult);
      }
      if (ClassUtils.isAssignableValue(ConstraintViolationException.class, exception)) {
         return exception.getMessage();
      }
      return null;
   }

}
