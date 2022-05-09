package com.igojeferson.geographic.distance.exception.handler;

import com.igojeferson.geographic.distance.dto.MessageDTO;
import com.igojeferson.geographic.distance.exception.ServiceValidationException;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceValidationExceptionHandler {

    @ExceptionHandler(value = ServiceValidationException.class )
    protected ResponseEntity<Object> handle(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new MessageDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage())
        );
    }
}
