package com.bitkap.event_manager_api.exception.handler;

import com.bitkap.event_manager_api.exception.*;
import com.bitkap.event_manager_api.exception.dtos.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.info("Error type : MethodArgumentNotValidException");
        log.error("Error type : MethodArgumentNotValidException");

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            log.error("Error field {} : {}", error.getField(), error.getDefaultMessage());
        });
        errors.put("timestamp", Instant.now().toString());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ObjectExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleObjectExistsException(ObjectExistsException exception) {
        log.info("Error type : ObjectExistsException");
        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponseDTO
                .builder()
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleObjectNotFoundException(ObjectNotFoundException exception) {
        log.info("Error type : ObjectNotFoundException");
        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDTO
                .builder()
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(ObjectExpiredException.class)
    public ResponseEntity<ErrorResponseDTO> handleObjectExpiredException(ObjectExpiredException exception) {
        log.info("Error type : ObjectExpiredException");
        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.GONE).body(ErrorResponseDTO
                .builder()
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleObjectExpiredException(AccessDeniedException exception) {
        log.info("Error type : AccessDeniedException");
        log.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponseDTO
                .builder()
                .timestamp(Instant.now())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(NotAcceptableDeletionException.class)
    public ResponseEntity<Map<String, Object>> handleObjectExpiredException(NotAcceptableDeletionException exception) {
        log.info("Error type : NotAcceptableException");
        log.error(exception.getMessage());

        Map<String, Object> errors = new HashMap<>();
        errors.put(
                "error",
                ErrorResponseDTO
                        .builder()
                        .timestamp(Instant.now())
                        .message(exception.getMessage())
                        .build()
        );
        errors.put(
                "items",
                exception.getItems()
        );

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }
}
