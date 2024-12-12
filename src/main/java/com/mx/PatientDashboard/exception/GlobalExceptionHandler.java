package com.mx.PatientDashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;
import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<ResponsePatientDto> handlePatientNotFoundException(
          PatientNotFoundException ex) {
    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("404");
    response.setErrors(Collections.singletonList(ex.getMessage()));
    response.setData(null);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ResponsePatientDto> handleDataIntegrityViolationException(
          DataIntegrityViolationException ex) {
    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("400");
    response.setErrors(Collections.singletonList("Duplicate entry: The provided email is already in use."));
    response.setData(null);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponsePatientDto> handleGeneralException(Exception ex) {
    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("500");
    response.setErrors(
            Collections.singletonList("An unexpected error occurred: " + ex.getMessage()));
    response.setData(null);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}