package com.thoughtworks.mall.infrastructure.exception.converter;

import com.thoughtworks.mall.infrastructure.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 400)
public class AuthenticationWebExceptionConverter extends AbstractExceptionConverter<AuthenticationException> {

    @Override
    public boolean supports(Class<?> throwableType) {
        return AuthenticationException.class.isAssignableFrom(throwableType);
    }

    @Override
    protected String getCode(AuthenticationException exception, MergedAnnotation<ResponseStatus> metadata) {
        var origin = getOriginThrowable(exception);
        if (origin instanceof BizException) {
            return ((BizException) origin).getHttpStatus().name();
        }
        return super.getCode(exception, metadata);
    }

    @Override
    protected HttpStatus getHttpStatus(AuthenticationException exception, MergedAnnotation<ResponseStatus> metadata) {
        var origin = getOriginThrowable(exception);
        if (origin instanceof BizException) {
            return ((BizException) origin).getHttpStatus();
        }

        if ((origin instanceof BadCredentialsException)) {
            return HttpStatus.BAD_REQUEST;
        }

        if (origin instanceof HttpRequestMethodNotSupportedException) {
            return HttpStatus.METHOD_NOT_ALLOWED;
        }

        if (origin instanceof HttpMediaTypeNotSupportedException) {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }

        return metadata.getValue("code", HttpStatus.class).orElse(HttpStatus.UNAUTHORIZED);
    }

    private Throwable getOriginThrowable(Throwable throwable) {
        return Optional.ofNullable(NestedExceptionUtils.getRootCause(throwable))
                .orElse(throwable);
    }
}
