package com.thoughtworks.mall.infrastructure.exception.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractExceptionConverter<E extends Throwable> implements WebExceptionConverter {

    private final Class<E> supportType;

    @SuppressWarnings("unchecked")
    protected AbstractExceptionConverter() {
        var parameterizedType = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.supportType = (Class<E>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public ResponseEntity<Map<String, Object>> convert(Throwable throwable, HttpServletRequest request) {
        var ex = supportType.cast(throwable);

        MergedAnnotation<ResponseStatus> metadata = MergedAnnotations
            .from(throwable.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);

        var status = getHttpStatus(ex, metadata);

        var body = new LinkedHashMap<String, Object>();
        body.compute("path", (key, value) -> request.getRequestURI());
        body.compute("status", (key, value) -> status.value());
        body.compute("timestamp", (key, value) -> new Date());
        body.compute("reason", (key, value) -> {
            var reason = getReason(ex, metadata);
            return CollectionUtils.isEmpty(reason) ? null : reason;
        });

        return ResponseEntity.status(status).body(body);
    }

    protected HttpStatus getHttpStatus(E ex, MergedAnnotation<ResponseStatus> metadata) {
        if (ex instanceof ResponseStatusException) {
            return ((ResponseStatusException) ex).getStatus();
        }
        return metadata.getValue("code", HttpStatus.class)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected Map<String, Object> getReason(E ex, MergedAnnotation<ResponseStatus> metadata) {
        var reason = new LinkedHashMap<String, Object>();
        reason.compute("code", (key, value) -> getCode(ex, metadata));
        reason.compute("messages", (key, value) -> getMessage(ex, metadata));
        return reason;
    }

    protected String getCode(E ex, MergedAnnotation<ResponseStatus> metadata) {
        return getHttpStatus(ex, metadata).name();
    }

    protected String getMessage(E ex, MergedAnnotation<ResponseStatus> metadata) {
        if (ex instanceof ResponseStatusException) {
            return ((ResponseStatusException) ex).getReason();
        }

        var reason = metadata.getValue("reason", String.class).orElse("");
        if (StringUtils.hasText(reason)) {
            return reason;
        }
        return (ex.getMessage() != null) ? ex.getMessage() : "";
    }

}
