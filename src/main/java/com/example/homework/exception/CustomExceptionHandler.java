package com.example.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EntityNotNullException.class)
     public ResponseEntity<?> entityNotNull(EntityNotNullException entityNotNullException){
         List<String> detail = new ArrayList<>();
         detail.add(entityNotNullException.getMessage());
         ErrorResponse errorResponse = new ErrorResponse("Entity Not Null",detail);
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

     }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFound(EntityNotFoundException entityNotFoundException){
        List<String> detail = new ArrayList<>();
        detail.add(entityNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Entity Not Found",detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EntityAlreadyException.class)
    public ResponseEntity<?> entityAlreadyExist(EntityAlreadyException entityAlreadyException){
        List<String> detail = new ArrayList<>();
        detail.add(entityAlreadyException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Entity Already Exist ",detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
