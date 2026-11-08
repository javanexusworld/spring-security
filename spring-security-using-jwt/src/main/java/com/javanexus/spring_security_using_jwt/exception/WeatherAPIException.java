package com.javanexus.spring_security_using_jwt.exception;

public class WeatherAPIException extends RuntimeException {
    public WeatherAPIException(String message) {
        super(message);
    }
}
