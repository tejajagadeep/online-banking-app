package com.onlinebanking.userservice.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), List.of("Please choose a different username", "Username must be unique"), LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), List.of("User not found"), LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of("Incorrect password"), LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MatchingPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMatchingPasswordException(MatchingPasswordException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of("Password matches password"), LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        List<String> strings = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> strings.add(error.getDefaultMessage()));
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", strings, LocalDateTime.now());
//        return new ResponseEntity<>(apiError, apiError.getStatus());
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {

        ApiError response = new ApiError();
        String message = ex.getMessage();
        String template = "messageTemplate='";
        String messageTemplate = message.substring(message.indexOf(template) + template.length(), message.indexOf("'", message.indexOf(template) + template.length()));
        response.setMessage(messageTemplate);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
