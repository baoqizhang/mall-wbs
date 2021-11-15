package com.thoughtworks.mall.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BizException extends RuntimeException {

   private final HttpStatus httpStatus;

   protected BizException(String message) {
      super(message);
      this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
   }

   protected BizException(Throwable throwable) {
      super(throwable);
      this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
   }

   protected BizException(HttpStatus httpStatus, String message) {
      super(message);
      this.httpStatus = httpStatus;
   }

   protected BizException(HttpStatus httpStatus, String message, Throwable throwable) {
      super(message, throwable);
      this.httpStatus = httpStatus;
   }
}
