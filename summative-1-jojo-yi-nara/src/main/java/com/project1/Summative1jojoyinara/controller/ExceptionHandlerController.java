package com.project1.Summative1jojoyinara.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.project1.Summative1jojoyinara.exception.ResponseStatusException;
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

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<CustomErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus returnHttpStatus = HttpStatus.NOT_FOUND;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<CustomErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        HttpStatus returnHttpStatus = HttpStatus.NOT_FOUND;
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

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<CustomErrorResponse> handleNumberFormatException(NumberFormatException ex) {
        HttpStatus returnHttpStatus = HttpStatus.NOT_FOUND;
        String customMessage = ex.getMessage();
        // Determine what is missing in the path
        if(customMessage.indexOf("manufacturer") > 0) {
            customMessage = "You may need to specify the manufacturer. e.g. /console/manufacturer/microsoft";
        } else if(customMessage.indexOf("esrbRating") > 0) {
            customMessage = "You may need to specify the esrbRating. e.g. /game/esrbRating/teen";
        } else if(customMessage.indexOf("title") > 0) {
            customMessage = "You may need to specify the title. e.g. /game/title/StartCraft";
        } else if(customMessage.indexOf("studio") > 0) {
            customMessage = "You may need to specify the studio. e.g. /game/studio/Electronic Arts";
        } else if(customMessage.indexOf("color") > 0) {
            customMessage = "You may need to specify the color. e.g. /t-shirt/color/blue";
        } else if(customMessage.indexOf("size") > 0) {
            customMessage = "You may need to specify the size. e.g. /t-shirt/size/Medium Arts";
        } else if(customMessage.indexOf("shirt") > 0) {
            customMessage = "Please make sure you have the correct path. e.g. /t-shirt";
        } else if(customMessage.indexOf("game") > 0) {
            customMessage = "Please make sure you have the correct path. e.g. /game";
        } else if(customMessage.indexOf("console") > 0) {
            customMessage = "Please make sure you have the correct path. e.g. /console";
        }
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
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
