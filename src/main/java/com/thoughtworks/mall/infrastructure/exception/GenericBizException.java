package com.thoughtworks.mall.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class GenericBizException extends BizException {

    public GenericBizException(String message) {
        super(message);
    }

    public GenericBizException(HttpStatus status, String message) {
        super(status, message);
    }
}
