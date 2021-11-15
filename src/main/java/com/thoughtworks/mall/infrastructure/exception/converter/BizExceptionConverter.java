package com.thoughtworks.mall.infrastructure.exception.converter;

import com.thoughtworks.mall.infrastructure.exception.BizException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class BizExceptionConverter extends AbstractExceptionConverter<BizException> {

   @Override
   public boolean supports(Class<?> throwableType) {
      return BizException.class.isAssignableFrom(throwableType);
   }

   @Override
   protected HttpStatus getHttpStatus(BizException ex, MergedAnnotation<ResponseStatus> metadata) {
      return ex.getHttpStatus();
   }

   @Override
   protected String getMessage(BizException ex, MergedAnnotation<ResponseStatus> metadata) {
      return ex.getMessage();
   }
}
