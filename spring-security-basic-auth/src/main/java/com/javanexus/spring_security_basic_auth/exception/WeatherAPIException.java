package com.javanexus.spring_security_basic_auth.exception;

public class WeatherAPIException extends RuntimeException {
    public WeatherAPIException(String message) {
        super(message);
    }
}
