package com.javanexus.spring_security_basic_auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WeatherAPIException.class)
    public ProblemDetail handleException(WeatherAPIException e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
        problemDetail.setTitle("Weather API Error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("service", "Weather Service");
        return problemDetail;
    }
}
