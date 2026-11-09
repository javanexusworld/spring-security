package com.javanexus.spring_security_using_jwt.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthException(AuthenticationException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Un authorized");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setProperty("service", "Weather Service");
        return problemDetail;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException(ExpiredJwtException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("JWT token Expired, please generate Again");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setProperty("service", "Weather Service");
        return problemDetail;
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ProblemDetail handleUserAlreadyRegisteredException(UserAlreadyRegisteredException exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("User already registered ");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setProperty("service", "Weather Service");
        return problemDetail;
    }




}
