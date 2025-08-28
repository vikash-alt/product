package com.product.exception;

import com.product.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDetails> handleProductNotFound(
            ProductNotFound ex,
            WebRequest request
    ){
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(true)
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(
            Exception ex,
            WebRequest request
    ){
        System.out.println(ex.getMessage());
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(true)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
