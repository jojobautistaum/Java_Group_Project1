package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.project1.Summative1jojoyinara.model.CustomErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<CustomErrorResponse> handleArithmeticException(EmptyResultDataAccessException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, "Cannot find the ID of the item you're trying to delete."+ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<CustomErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {JsonParseException.class})
    public ResponseEntity<CustomErrorResponse> handleJsonParseException(JsonParseException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        String customMessage = ex.getMessage();
        // Extract only the default message
        if(customMessage.indexOf("default message") > 0) {
            int start = customMessage.indexOf("default message");
            customMessage = customMessage.substring(start, customMessage.length() - 1);
        }
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

}
