package com.fmiunibuc.FoodDeliveryApp.exception.advice;

import com.fmiunibuc.FoodDeliveryApp.exception.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({RestaurantNotFoundException.class, UserNotFoundException.class, DriverNotFoundException.class, ProductNotFoundException.class, OrderNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getBindingResult().getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.joining(", "))
                );
    }

}
