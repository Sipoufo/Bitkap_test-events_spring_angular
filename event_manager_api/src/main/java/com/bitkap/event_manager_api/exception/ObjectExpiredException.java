package com.bitkap.event_manager_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ObjectExpiredException extends RuntimeException {
    public ObjectExpiredException(String message) {super(message);}
}
