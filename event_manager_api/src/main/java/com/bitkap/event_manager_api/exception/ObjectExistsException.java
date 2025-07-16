package com.bitkap.event_manager_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ObjectExistsException extends RuntimeException {
    public ObjectExistsException(String message) {super(message);}
}
