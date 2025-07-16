package com.bitkap.event_manager_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
@Getter
public class NotAcceptableDeletionException extends RuntimeException {
    List<String> items = new ArrayList<String>();

    public NotAcceptableDeletionException(String message) {super(message);}
    public NotAcceptableDeletionException(String message, List<String> items) {
        super(message);
        this.items = items;
    }
}
