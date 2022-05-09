package com.igojeferson.geographic.distance.exception.handler;

import com.igojeferson.geographic.distance.dto.MessageDTO;
import com.igojeferson.geographic.distance.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity handle(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new MessageDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage())
        );
    }
}
