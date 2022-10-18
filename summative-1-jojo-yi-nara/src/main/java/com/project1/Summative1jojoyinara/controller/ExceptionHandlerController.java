package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.CustomErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
