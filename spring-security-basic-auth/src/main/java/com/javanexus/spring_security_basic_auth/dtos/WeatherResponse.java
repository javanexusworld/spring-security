package com.javanexus.spring_security_basic_auth.dtos;

public record WeatherResponse( String city,
                               double temperature,
                               double feelsLike,
                               int humidity,
                               String description,
                               double windSpeed) {
}
