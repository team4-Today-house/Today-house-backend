package com.example.todayhousebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

  @ExceptionHandler(value = { IllegalArgumentException.class })
  public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
    RestApiException restApiException = new RestApiException();
    restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
    restApiException.setStatusCode(HttpStatus.BAD_REQUEST.value());
    restApiException.setErrorMessage(ex.getMessage());

    return new ResponseEntity(
        restApiException,
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(value = { ApiException.class })
  public ResponseEntity<Object> handleApiRequestException(ApiException apiex) {
    RestApiException restApiException = new RestApiException();
    restApiException.setHttpStatus(apiex.getErrorCode().getStatus());
    restApiException.setStatusCode(apiex.getErrorCode().getStatusCode());
    restApiException.setErrorMessage(apiex.getErrorCode().getMessage());

    return new ResponseEntity(
            restApiException,
            apiex.getErrorCode().getStatus()
    );
  }

}