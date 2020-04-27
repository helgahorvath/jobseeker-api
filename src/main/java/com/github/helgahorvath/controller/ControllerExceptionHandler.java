package com.github.helgahorvath.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(value = {DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleRegistrationExceptions() {
    var body = new HashMap<>();
    body.put("message", "Email already exists");
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
    var body = new HashMap<>();
    body.put("message", ((ConstraintViolation) exception.getConstraintViolations().toArray()[0]).getMessage());
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public ResponseEntity<Object> handleUnauthorized(AccessDeniedException exception) {
    var body = new HashMap<>();
    body.put("message", exception.getMessage());
    return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);

  }

}
